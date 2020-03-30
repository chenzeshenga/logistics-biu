package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.mapper.OrderMapper;
import com.abc.chenzeshenga.logistics.model.ManualOrder;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Service
public class OrderService extends ServiceImpl<OrderMapper, ManualOrder> {

  public Page<ManualOrder> list(Page page, String cname, String type, String status) {
    return page.setRecords(baseMapper.list(page, cname, type, status));
  }

  public Page<ManualOrder> listV2(Page page, String cname, String type, String status) {
    long from = (page.getCurrent() - 1) * (long) page.getSize();
    long to = page.getCurrent() * (long) page.getSize();
    page.setRecords(baseMapper.listV2(cname, type, status, from, to));
    page.setTotal(baseMapper.countList(cname, type, status));
    return page;
  }

  public Page<ManualOrder> listByRange(
      Page page,
      String cname,
      String type,
      String status,
      Date from,
      Date to,
      String ordno,
      String creator,
      String channelCode,
      String trackNo,
      String userCustomOrderNo) {
    return page.setRecords(
        baseMapper.listByRange(
            page,
            cname,
            type,
            status,
            from,
            to,
            ordno,
            creator,
            channelCode,
            trackNo,
            userCustomOrderNo));
  }
}
