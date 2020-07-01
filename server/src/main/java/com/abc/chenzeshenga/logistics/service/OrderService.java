package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.mapper.OrderMapper;
import com.abc.chenzeshenga.logistics.model.ManualOrder;
import com.abc.chenzeshenga.logistics.model.ManualOrderContent;
import com.abc.chenzeshenga.logistics.model.ord.OrderPackage;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.Date;
import java.util.List;

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
      String type,
      String status,
      Date from,
      Date to,
      String ordno,
      String creator,
      String channelCode,
      String trackNo,
      String userCustomOrderNo,
      int pickup) {
    Page<ManualOrder> manualOrderPage = new Page<>();
    long fromIdx = (page.getCurrent() - 1) * (long) page.getSize();
    long toIdx = page.getCurrent() * (long) page.getSize();
    List<ManualOrder> manualOrderList =
        baseMapper.listByRange(
            type,
            status,
            from,
            to,
            ordno,
            creator,
            channelCode,
            trackNo,
            userCustomOrderNo,
            pickup,
            fromIdx,
            toIdx);
    for (ManualOrder manualOrder : manualOrderList) {
      List<ManualOrderContent> manualOrderContentList =
          baseMapper.listContent2(manualOrder.getOrderNo());
      List<OrderPackage> orderPackageList = baseMapper.listPackage(manualOrder.getOrderNo());
      manualOrder.setManualOrderContents(manualOrderContentList);
      manualOrder.setOrderPackageList(orderPackageList);
    }
    manualOrderPage.setRecords(manualOrderList);
    manualOrderPage.setSize(page.getSize());
    manualOrderPage.setTotal(
        baseMapper.countByRange(
            type,
            status,
            from,
            to,
            ordno,
            creator,
            channelCode,
            trackNo,
            userCustomOrderNo,
            pickup));
    manualOrderPage.setCurrent(page.getCurrent());
    return manualOrderPage;
  }

  public void pickup() {}
}
