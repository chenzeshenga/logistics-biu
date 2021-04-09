package com.abc.chenzeshenga.logistics.mapper.fee;

import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.abc.chenzeshenga.logistics.model.fee.CustomerAccountInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenzeshenga
 */
@Mapper
public interface CustomerAccountMapper extends BaseMapper<CustomerAccountInfo> {

    /**
     * 清空统计表
     */
    void truncate();

    List<CustomerAccountInfo> selectCustomerAccountList(@Param("sqlLimit") SqlLimit sqlLimit, @Param("userId") String userId);

    long countCustomerAccountList(@Param("userId") String userId);

}
