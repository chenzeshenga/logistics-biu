package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.mapper.ProductMapper;
import com.abc.chenzeshenga.logistics.model.Product;
import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Service
public class ProductService extends ServiceImpl<ProductMapper, Product> {

  public List<Product> listByStatusWithUser(Product searchProduct, SqlLimit sqlLimit) {
    return baseMapper.listByStatusWithUser(searchProduct, sqlLimit);
  }

  public List<Product> listByStatus(Product searchProduct, SqlLimit sqlLimit) {
    return baseMapper.listByStatus(searchProduct, sqlLimit);
  }

  public long countByStatus(Product searchProduct) {
    return baseMapper.countByStatus(searchProduct);
  }

  public Product selectProductByDySku(String dySku) {
    return baseMapper.selectProductByDySku(dySku);
  }
}
