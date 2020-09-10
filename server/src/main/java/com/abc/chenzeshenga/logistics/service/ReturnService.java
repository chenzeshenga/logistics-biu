package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.mapper.ReturnMapper;
import com.abc.chenzeshenga.logistics.model.Return;
import com.abc.chenzeshenga.logistics.model.ReturnContent;
import com.abc.chenzeshenga.logistics.model.common.PageData;
import com.abc.chenzeshenga.logistics.model.common.PageQueryEntity;
import com.abc.chenzeshenga.logistics.model.common.Pagination;
import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.abc.chenzeshenga.logistics.service.returning.ReturnOrdContentService;
import com.abc.chenzeshenga.logistics.service.user.UserCommonService;
import com.abc.chenzeshenga.logistics.util.SqlUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chenzesheng
 * @version 1.0
 * @since 2019.7.20
 */
@Service
public class ReturnService extends ServiceImpl<ReturnMapper, Return> {

    @Resource
    private ReturnMapper returnMapper;

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

    public Page<Return> listAll(Page page, String status, Date from, Date to) {
        return page.setRecords(baseMapper.listAll(page, status, from, to));
    }

    public Return getReturnOrdDetail(String returnNo) {
        Return returnOrd = selectById(returnNo);
        List<ReturnContent> returnContentList = returnOrdContentService.listContent(returnNo);
        if (!returnContentList.isEmpty()) {
            returnOrd.setContentList(returnContentList);
        }
        return returnOrd;
    }

    public void updatePkgInfo(Return returnOrd) {
        baseMapper.updatePkgInfo(returnOrd);
    }
}
