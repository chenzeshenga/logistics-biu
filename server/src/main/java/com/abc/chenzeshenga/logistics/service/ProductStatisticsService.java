package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.mapper.ProductStatisticsMapper;
import com.abc.chenzeshenga.logistics.model.ProductStatistics;
import com.abc.chenzeshenga.logistics.model.common.PageData;
import com.abc.chenzeshenga.logistics.model.common.PageQueryEntity;
import com.abc.chenzeshenga.logistics.model.common.Pagination;
import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.abc.chenzeshenga.logistics.model.v2.statistics.ProductInWarehouseStatistics;
import com.abc.chenzeshenga.logistics.model.v2.statistics.ProductInWarehouseStatisticsReq;
import com.abc.chenzeshenga.logistics.service.product.ProductInWarehouseRecordService;
import com.abc.chenzeshenga.logistics.util.DateUtil;
import com.abc.chenzeshenga.logistics.util.SnowflakeIdWorker;
import com.abc.chenzeshenga.logistics.util.SqlUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 * @since 20190809
 */
@Service
@Slf4j
public class ProductStatisticsService
        extends ServiceImpl<ProductStatisticsMapper, ProductStatistics> {

    private final ProductInWarehouseRecordService productInWarehouseRecordService;

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
        baseMapper.deleteAll();
        List<ProductInWarehouseStatistics> productInWarehouseStatisticsList = baseMapper.triggerCount();
        productInWarehouseStatisticsList.forEach(productInWarehouseStatistics -> productInWarehouseStatistics.setUuid(SnowflakeIdWorker.generateStrId()));
        baseMapper.insertProductInWarehouseBatch(productInWarehouseStatisticsList);
    }

    public PageData<ProductInWarehouseStatistics> listProductStatistics(PageQueryEntity<ProductInWarehouseStatisticsReq> productInWarehouseStatisticsReqPageQueryEntity) {
        Pagination pagination = productInWarehouseStatisticsReqPageQueryEntity.getPagination();
        SqlLimit sqlLimit = SqlUtils.generateSqlLimit(pagination);
        ProductInWarehouseStatisticsReq productInWarehouseStatisticsReq = productInWarehouseStatisticsReqPageQueryEntity.getEntity();
        List<ProductInWarehouseStatistics> productInWarehouseStatisticsList = baseMapper.select(sqlLimit, productInWarehouseStatisticsReq);
        long total = baseMapper.count(productInWarehouseStatisticsReq);
        PageData<ProductInWarehouseStatistics> productInWarehouseStatisticsPageData = new PageData<>();
        productInWarehouseStatisticsPageData.setData(productInWarehouseStatisticsList);
        productInWarehouseStatisticsPageData.setTotal(total);
        return productInWarehouseStatisticsPageData;
    }

}
