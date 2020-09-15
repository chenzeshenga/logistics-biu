package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.mapper.ReturnMapper;
import com.abc.chenzeshenga.logistics.mapper.warehouse.ProductInWarehouseRecordMapper;
import com.abc.chenzeshenga.logistics.model.Return;
import com.abc.chenzeshenga.logistics.model.ReturnContent;
import com.abc.chenzeshenga.logistics.model.claim.ClaimContentDealing;
import com.abc.chenzeshenga.logistics.model.claim.ClaimPackage;
import com.abc.chenzeshenga.logistics.model.common.PageData;
import com.abc.chenzeshenga.logistics.model.common.PageQueryEntity;
import com.abc.chenzeshenga.logistics.model.common.Pagination;
import com.abc.chenzeshenga.logistics.model.warehouse.ProductInWarehouse;
import com.abc.chenzeshenga.logistics.service.returning.ReturnOrdContentService;
import com.abc.chenzeshenga.logistics.service.user.UserCommonService;
import com.abc.chenzeshenga.logistics.util.SqlUtils;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.chenzeshenga.logistics.util.UuidUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author chenzesheng
 * @version 1.0
 * @since 2019.7.20
 */
@Service
@Slf4j
public class ReturnService extends ServiceImpl<ReturnMapper, Return> {

    @Resource
    private ReturnMapper returnMapper;

    @Resource
    private ProductInWarehouseRecordMapper productInWarehouseRecordMapper;

    private final ReturnOrdContentService returnOrdContentService;

    private final UserCommonService userCommonService;

    @Autowired
    public ReturnService(ReturnOrdContentService returnOrdContentService, UserCommonService userCommonService) {
        this.returnOrdContentService = returnOrdContentService;
        this.userCommonService = userCommonService;
    }

    @Deprecated
    public Page<Return> list(Page page, String status, Date from, Date to) {
        return page.setRecords(baseMapper.list(page, status, from, to));
    }

    /**
     * 分页查询退货订单
     *
     * @param returnPageQueryEntity 分页查询参数
     * @return 退货单分页
     */
    public PageData<Return> listV2(PageQueryEntity<Return> returnPageQueryEntity) {
        PageData<Return> result = new PageData<>();
        Return returnEntity = returnPageQueryEntity.getEntity();
        Pagination pagination = returnPageQueryEntity.getPagination();
        List<Return> returnList = returnMapper.listV2(returnEntity, SqlUtils.generateSqlLimit(pagination));
        long total = returnMapper.count(returnEntity);
        result.setCurrent(pagination.getCurrent());
        result.setTotal(total);
        result.setSize(pagination.getSize());
        result.setData(returnList);
        return result;
    }

    /**
     * 根据退货单号认领退货单
     *
     * @param returnNo 退货单号
     */
    public void claimReturnOrd(String returnNo) {
        returnMapper.claimReturnNo(returnNo, UserUtils.getUserName());
    }

    /**
     * 每天零点10分运行定时任务
     * 将创建时间在 14天的退货单的状态更新为 历史
     */
    @PostConstruct
    @Scheduled(cron = "0 10 0 * * ?")
    public void archiveReturnOrd() {
        log.info("ReturnService.archiveReturnOrd start");
        int total = returnMapper.archiveReturnOrd();
        log.info("total {} record influenced", total);
        log.info("ReturnService.archiveReturnOrd end");
    }

    public Page<Return> listAll(Page page, String status, Date from, Date to) {
        return page.setRecords(baseMapper.listAll(page, status, from, to));
    }

    /**
     * 根据退货单号获取退货单信息
     *
     * @param returnNo 退货单号
     * @return 退货单
     */
    public Return getReturnOrdDetail(String returnNo) {
        return returnMapper.selectByPk(returnNo);
    }

    public void updatePkgInfo(Return returnOrd) {
        baseMapper.updatePkgInfo(returnOrd);
    }

    /**
     * 记录退货单包裹体积重量
     *
     * @param claimPackageList 包裹列表
     */
    public void updateClaimPackage(List<ClaimPackage> claimPackageList) {
        returnMapper.dropClaimPackage(claimPackageList.get(0).getReturnNo());
        claimPackageList.forEach(claimPackage -> claimPackage.setUuid(UuidUtils.uuid()));
        returnMapper.insertClaimPackage(claimPackageList);
    }

    /**
     * 根据退货单号更新状态
     *
     * @param returnNo 退货单号
     * @param status   状态
     */
    public void updateStatus(String returnNo, String status) {
        returnMapper.updateStatus(returnNo, status);
    }

    /**
     * 记录退货单处理内容
     *
     * @param claimContentDealingList 退货单处理
     */
    public void updateClaimContentDealing(List<ClaimContentDealing> claimContentDealingList) {
        String returnNo = claimContentDealingList.get(0).getReturnNo();
        returnMapper.dropClaimContentDealing(returnNo);
        Return returnEntity = returnMapper.selectByPk(returnNo);
        claimContentDealingList.forEach(claimContentDealing -> {
            claimContentDealing.setUuid(UuidUtils.uuid());
            String dealWith = claimContentDealing.getDealWith();
            if ("重新上架".equals(dealWith)) {
                ProductInWarehouse productInWarehouse = new ProductInWarehouse();
                productInWarehouse.setUuid(UuidUtils.uuid());
                productInWarehouse.setNum(claimContentDealing.getNum());
                productInWarehouse.setInTime(new Date());
                productInWarehouse.setDySku(claimContentDealing.getSku());
                productInWarehouse.setShelfNo(claimContentDealing.getShelfNo());
                productInWarehouse.setOwner(returnEntity.getCreator());
                productInWarehouse.setWarehousingNo("退货" + returnNo);
                productInWarehouseRecordMapper.insert(productInWarehouse);
            }
        });
        returnMapper.insertClaimContentDealing(claimContentDealingList);
    }

}
