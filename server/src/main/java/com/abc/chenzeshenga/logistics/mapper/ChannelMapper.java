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

    /**
     * 用于取代list方法, where 字段还未添加到数据库表中
     *
     * @param where 试用范围
     * @return 渠道列表
     */
    List<ChannelLabel> listByWhere(String where);

    List<Channel> page(Pagination pagination);

    List<Channel> pageReg(Pagination pagination, String reg);

    int quickEnableList(List<String> channelCodes);

    int quickDisableList(List<String> channelCodes);

}