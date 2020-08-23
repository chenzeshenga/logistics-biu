package com.abc.chenzeshenga.logistics.service.statistics;

import com.abc.chenzeshenga.logistics.mapper.statistics.ProductInWarehouseMapper;
import com.abc.chenzeshenga.logistics.model.warehouse.ProductInWarehouseSummary;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @since 2020-01-29 23:34
 */
@Service
public class ProductInWarehouseService
    extends ServiceImpl<ProductInWarehouseMapper, ProductInWarehouseSummary> {

  public List<ProductInWarehouseSummary> fetchProductInWarehouseWithManagerRole(
      String dySku, String name, String owner, long from, long size) {
    return baseMapper.fetchProductInWarehouseWithManagerRole(dySku, name, owner, from, size);
  }

  public List<ProductInWarehouseSummary> fetchAllProductInWarehouseWithManagerRole(
      String dySku, String name, String owner) {
    return baseMapper.fetchAllProductInWarehouseWithManagerRole(dySku, name, owner);
  }

  public List<ProductInWarehouseSummary> fetchProductInWarehouseWithUserRole(
      String dySku, String name, String username, long from, long size) {
    return baseMapper.fetchProductInWarehouseWithUserRole(dySku, name, username, from, size);
  }

  public long countProductInWarehouseWithUserRole(String dySku, String name, String owner) {
    return baseMapper.countProductInWarehouseWithUserRole(dySku, name, owner);
  }

  public long countProductInWarehouseWithManagerRole(String dySku, String name, String owner) {
    return baseMapper.countProductInWarehouseWithManagerRole(dySku, name, owner);
  }

  public List<ProductInWarehouseSummary> fetchAllProductInWarehouseWithUserRole(
      String dySku, String name, String username) {
    return baseMapper.fetchAllProductInWarehouseWithUserRole(dySku, name, username);
  }
}
