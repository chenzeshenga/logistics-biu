package com.abc.chenzeshenga.logistics.service.fee;

import com.abc.chenzeshenga.logistics.mapper.fee.RechargeInfoMapper;
import com.abc.chenzeshenga.logistics.model.common.PageData;
import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.abc.chenzeshenga.logistics.model.fee.CustomerFee;
import com.abc.chenzeshenga.logistics.model.fee.CustomerFeeReq;
import com.abc.chenzeshenga.logistics.model.fee.RechargeInfo;
import com.abc.chenzeshenga.logistics.model.fee.RechargeInfoReq;
import com.abc.chenzeshenga.logistics.util.SnowflakeIdWorker;
import com.abc.chenzeshenga.logistics.util.SqlUtils;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author chenzeshenga
 */
@Service
public class RechargeInfoService extends ServiceImpl<RechargeInfoMapper, RechargeInfo> {

    public PageData<RechargeInfo> selectRechargeInfo(RechargeInfoReq rechargeInfoReq) {
        SqlLimit sqlLimit = SqlUtils.generateSqlLimit(rechargeInfoReq);
        List<RechargeInfo> rechargeInfoList = baseMapper.selectRechargeInfo(sqlLimit, rechargeInfoReq.getUserId());
        long total = baseMapper.countRechargeInfoList(rechargeInfoReq.getUserId());
        rechargeInfoReq.setTotal(total);
        PageData<RechargeInfo> rechargeInfoPageData = new PageData<>();
        rechargeInfoPageData.setData(rechargeInfoList);
        rechargeInfoPageData.setPagination(rechargeInfoReq);
        return rechargeInfoPageData;
    }

    @Override
    public boolean insert(RechargeInfo entity) {
        entity.setUuid(SnowflakeIdWorker.generateStrId());
        return super.insert(entity);
    }
}
