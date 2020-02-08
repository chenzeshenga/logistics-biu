package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.mapper.ChannelMapper;
import com.abc.chenzeshenga.logistics.model.Channel;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Service
public class ChannelService extends ServiceImpl<ChannelMapper, Channel> {

  public Page<Channel> list(Page page) {
    return page.setRecords(baseMapper.page(page));
  }

  public Page<Channel> listReg(Page page, String reg) {
    return page.setRecords(baseMapper.pageReg(page, reg));
  }
}
