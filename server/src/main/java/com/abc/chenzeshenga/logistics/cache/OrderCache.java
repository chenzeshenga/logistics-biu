package com.abc.chenzeshenga.logistics.cache;

import com.abc.chenzeshenga.logistics.mapper.OrderMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author chenzesheng
 * @version 1.0
 */
@Component
public class OrderCache {

    @Resource
    private OrderMapper orderMapper;

    private String orderSeq;

    @Scheduled(cron = "0 0 0 * * ?")
    @PostConstruct
    public void init() {
        this.orderSeq = String.valueOf(orderMapper.count());
    }

    public String getOrderSeq() {
        String resultSeq = this.orderSeq;
        addSeq();
        return resultSeq;
    }

    public void addSeq() {
        this.orderSeq = String.valueOf(Integer.valueOf(this.orderSeq) + 1);
    }

}
