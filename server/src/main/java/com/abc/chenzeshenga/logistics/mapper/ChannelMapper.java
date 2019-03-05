package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.Channel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper public interface ChannelMapper {

    List<Channel> list();

}
