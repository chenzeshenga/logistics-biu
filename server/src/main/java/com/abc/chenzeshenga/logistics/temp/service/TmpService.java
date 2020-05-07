package com.abc.chenzeshenga.logistics.temp.service;

import com.abc.chenzeshenga.logistics.mapper.OrderMapper;
import com.abc.chenzeshenga.logistics.temp.entity.TmpOrder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chenzeshenga
 * @since 2020-05-07 22:54
 */
@Service
public class TmpService {

  @Resource private OrderMapper orderMapper;

  public void updateOrder(TmpOrder tmpOrder) {
    orderMapper.updateOrder(tmpOrder);
  }
}
