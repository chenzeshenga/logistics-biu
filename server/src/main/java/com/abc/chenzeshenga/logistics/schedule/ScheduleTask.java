package com.abc.chenzeshenga.logistics.schedule;

import com.abc.chenzeshenga.logistics.mapper.OrderMapper;
import com.abc.chenzeshenga.logistics.mapper.ProductMapper;
import com.abc.chenzeshenga.logistics.mapper.ProductStatisticsMapper;
import com.abc.chenzeshenga.logistics.model.ManualOrder;
import com.abc.chenzeshenga.logistics.model.ManualOrderContent;
import com.abc.chenzeshenga.logistics.model.Product;
import com.abc.chenzeshenga.logistics.model.ProductStatistics;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author chenzesheng
 * @version 1.0
 */
@Service @Slf4j public class ScheduleTask {

    @Resource private OrderMapper orderMapper;

    @Resource private ProductStatisticsMapper productStatisticsMapper;

    @Resource private ProductMapper productMapper;

    @Scheduled(fixedDelay = 120000) public void countProduct() {
        log.info("scheduled task countProduct begin");
        List<ProductStatistics> productStatisticsList = new ArrayList<>();
        List<ManualOrder> manualOrderList = orderMapper.listAll();
        List<Product> productList = productMapper.listByStatus(new Page(0, 999), "1");
        Map<String, ProductStatistics> productStatisticsMap = new HashMap<>(12);
        for (Product product : productList) {
            ProductStatistics productStatistics = new ProductStatistics();
            productStatistics.setSku(product.getSku());
            productStatistics.setDysku(product.getDySku());
            productStatistics.setOwner(product.getCreatedBy());
            productStatistics.setTotalNum(product.getAvaNum());
            productStatisticsMap
                .put(product.getSku() + "/" + product.getDySku() + ":" + product.getCreatedBy(), productStatistics);
        }
        manualOrderList.forEach(manualOrder -> {
            if ("1".equals(manualOrder.getCategory()) && "2".equals(manualOrder.getStatus())) {
                List<ManualOrderContent> manualOrderContentList = manualOrder.getManualOrderContents();
                if (!manualOrderList.isEmpty()) {
                    manualOrderContentList.forEach(manualOrderContent -> {
                        String sku = manualOrderContent.getSku();
                        String owner = manualOrder.getCreator();
                        String key = sku + ":" + owner;
                        ProductStatistics productStatistics = productStatisticsMap.get(key);
                        if (productStatistics == null) {
                            productStatistics = new ProductStatistics();
                            productStatistics.setSku(sku.split("/")[0]);
                            productStatistics.setDysku(sku.split("/")[1]);
                            productStatistics.setOwner(owner);
                            productStatistics.setStatisticalTime(new Date());
                            productStatistics.setPendingPickupNum(String.valueOf(
                                Integer.valueOf(manualOrderContent.getNum()) - Integer
                                    .valueOf(manualOrderContent.getPicked())));
                            productStatistics
                                .setPendingDeliverNum(String.valueOf(Integer.valueOf(manualOrderContent.getPicked())));
                        } else {
                            productStatistics.setStatisticalTime(new Date());
                            productStatistics.setPendingPickupNum(String.valueOf(
                                Integer.valueOf(manualOrderContent.getNum()) - Integer
                                    .valueOf(manualOrderContent.getPicked()) + Integer
                                    .valueOf(productStatistics.getPendingPickupNum())));
                            productStatistics.setPendingDeliverNum(String.valueOf(Integer.valueOf(
                                Integer.valueOf(manualOrderContent.getPicked()) + Integer
                                    .valueOf(productStatistics.getPendingDeliverNum()))));
                        }
                        productStatisticsMap.put(key, productStatistics);
                    });
                }
            }
        });
        for (Map.Entry<String, ProductStatistics> entry : productStatisticsMap.entrySet()) {
            productStatisticsList.add(entry.getValue());
        }
        productStatisticsMapper.deleteAll();
        if (!productStatisticsList.isEmpty()) {
            productStatisticsMapper.insertBatch(productStatisticsList);
        }
        log.info("scheduled task countProduct end");
    }

}
