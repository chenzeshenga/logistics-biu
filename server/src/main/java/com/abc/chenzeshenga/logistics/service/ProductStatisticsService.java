package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.mapper.ProductStatisticsMapper;
import com.abc.chenzeshenga.logistics.model.ProductStatistics;
import com.abc.chenzeshenga.logistics.service.product.ProductInWarehouseRecordService;
import com.abc.chenzeshenga.logistics.util.DateUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @author chenzeshenga
 * @version 1.0
 * @since 20190809
 */
@Service
@Slf4j
public class ProductStatisticsService
        extends ServiceImpl<ProductStatisticsMapper, ProductStatistics> {

    private ProductInWarehouseRecordService productInWarehouseRecordService;

    @Autowired
    public ProductStatisticsService(ProductInWarehouseRecordService productInWarehouseRecordService) {
        this.productInWarehouseRecordService = productInWarehouseRecordService;
    }

    public Page<ProductStatistics> selectAll(Page page) {
        return page.setRecords(baseMapper.selectAll(page));
    }

    public Page<ProductStatistics> selectAllBySearch(
            Page page, String sku, String name, String owner) {
        return page.setRecords(baseMapper.selectAllBySearch(page, sku, name, owner));
    }

    @PostConstruct
    @Scheduled(cron = "0 0 0 * * ?")
    public void triggerStatistics() {
        log.info("statistics start at {}", DateUtil.getStrFromDate(new Date()));
        productInWarehouseRecordService.listCurrentDayProduct();
        log.info("statistics end at {}", DateUtil.getStrFromDate(new Date()));
    }

}
