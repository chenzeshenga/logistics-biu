package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.Return;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenzesheng
 * @version 1.0
 * @since 2019.7.19
 */
@Mapper public interface ReturnMapper extends BaseMapper<Return> {

    Return getReturnSeq();

}
