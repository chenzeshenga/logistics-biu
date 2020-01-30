package com.abc.chenzeshenga.logistics.service.statistics;

import java.util.List;

import com.abc.chenzeshenga.logistics.mapper.statistics.ProductInWarehouseMapper;
import com.abc.chenzeshenga.logistics.model.warehouse.ProductInWarehouseSummary;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @since 2020-01-29 23:34
 */
@Service
public class ProductInWarehouseService extends ServiceImpl<ProductInWarehouseMapper, ProductInWarehouseSummary> {

    public List<ProductInWarehouseSummary> fetchProductInWarehouseWithManagerRole(Page page, String sku, String name,
            String owner) {
        List<ProductInWarehouseSummary> productInWarehouseSummaries = baseMapper
                .fetchProductInWarehouseWithManagerRole(page, sku, name, owner);
        return productInWarehouseSummaries;
    }

    public List<ProductInWarehouseSummary> fetchProductInWarehouseWithUserRole(Page page, String sku, String name,
            String username) {
        List<ProductInWarehouseSummary> productInWarehouseSummaries = baseMapper
                .fetchProductInWarehouseWithUserRole(page, sku, name, username);
        return productInWarehouseSummaries;
    }

}
