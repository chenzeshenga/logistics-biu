package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.Channel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 * @file ChannelMapper
 * @email chenzeshenga@163.com
 * @createOn 2019-3-6 1:17
 * @updateOn 2019-3-6 1:17
 */
@Mapper public interface ChannelMapper {

    List<Channel> list();

}
