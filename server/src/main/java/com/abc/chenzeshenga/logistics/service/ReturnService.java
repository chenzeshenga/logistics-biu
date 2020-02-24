package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.mapper.ReturnMapper;
import com.abc.chenzeshenga.logistics.model.Return;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 * @author chenzesheng
 * @version 1.0
 * @since 2019.7.20
 */
@Service
public class ReturnService extends ServiceImpl<ReturnMapper, Return> {

  public Page<Return> list(Page page, String status, Date from, Date to) {
    return page.setRecords(baseMapper.list(page, status, from, to));
  }

  public Page<Return> listAll(Page page, String status, Date from, Date to) {
    return page.setRecords(baseMapper.listAll(page, status, from, to));
  }

  public Return getReturnOrdDetail(String returnNo) {
    Return returnOrd = selectById(returnNo);
    return returnOrd;
  }
}
