package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.Channel;
import com.abc.chenzeshenga.logistics.model.ChannelLabel;
import com.abc.chenzeshenga.logistics.model.ManualOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Mapper public interface ChannelMapper extends BaseMapper<Channel> {

    int deleteByPrimaryKey(String channelCode);

    @Override Integer insert(Channel record);

    int insertSelective(Channel record);

    Channel selectByPrimaryKey(String channelCode);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);

    List<ChannelLabel> list();

    List<Channel> page(Pagination pagination);

}