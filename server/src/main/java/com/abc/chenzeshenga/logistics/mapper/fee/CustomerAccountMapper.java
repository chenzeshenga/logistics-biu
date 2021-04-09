package com.abc.chenzeshenga.logistics.mapper.fee;

import com.abc.chenzeshenga.logistics.model.fee.CustomerAccountInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerAccountMapper extends BaseMapper<CustomerAccountInfo> {

    /**
     * 清空统计表
     */
    void truncate();

}
