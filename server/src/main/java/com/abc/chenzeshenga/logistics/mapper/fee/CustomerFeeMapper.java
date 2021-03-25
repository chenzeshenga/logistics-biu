package com.abc.chenzeshenga.logistics.mapper.fee;

import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.abc.chenzeshenga.logistics.model.fee.CustomerFee;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenzeshenga
 */
@Mapper
public interface CustomerFeeMapper extends BaseMapper<CustomerFee> {

    /**
     * 查询费用列表
     *
     * @param sqlLimit   分页参数
     * @param startMonth 开始月份
     * @param endMonth   结束月份
     * @param userId     用户id
     * @return 费用列表
     */
    List<CustomerFee> selectCustomerFeeList(@Param("sqlLimit") SqlLimit sqlLimit, @Param("startMonth") Integer startMonth, @Param("endMonth") Integer endMonth, @Param("userId") String userId);

    /**
     * 计数费用列表
     *
     * @param startMonth 开始月份
     * @param endMonth   结束月份
     * @param userId     用户id
     * @return 计数
     */
    long countCustomerFeeList(@Param("startMonth") Integer startMonth, @Param("endMonth") Integer endMonth, @Param("userId") String userId);

    /**
     * 根据id获取费用详情
     *
     * @param id uuid
     * @return 费用详情
     */
    @Override
    CustomerFee selectById(@Param("uuid") Serializable id);
}
