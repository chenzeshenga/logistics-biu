package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.mapper.OrderMapper;
import com.abc.chenzeshenga.logistics.model.ManualOrder;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Service public class OrderService extends ServiceImpl<OrderMapper, ManualOrder> {

    public Page<ManualOrder> list(Page page, String cname, String type, String status) {
        return page.setRecords(baseMapper.list(page, cname, type, status));
    }

}
