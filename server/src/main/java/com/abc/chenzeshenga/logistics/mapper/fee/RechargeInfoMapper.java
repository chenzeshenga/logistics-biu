package com.abc.chenzeshenga.logistics.mapper.fee;

import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.abc.chenzeshenga.logistics.model.fee.RechargeInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenzeshenga
 */
@Mapper
public interface RechargeInfoMapper extends BaseMapper<RechargeInfo> {

    /**
     * 根据userId查询充值记录
     *
     * @param sqlLimit 分页参数
     * @param userId   用户id
     * @return 充值记录
     */
    List<RechargeInfo> selectRechargeInfo(@Param("sqlLimit") SqlLimit sqlLimit, @Param("userId") String userId);

    /**
     * 统计充值记录
     *
     * @param userId 用户id
     * @return 总数
     */
    long countRechargeInfoList(@Param("userId") String userId);

    @Override
    Integer insert(RechargeInfo entity);
}
