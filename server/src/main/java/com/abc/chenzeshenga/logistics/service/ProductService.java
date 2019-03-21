package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.mapper.ProductMapper;
import com.abc.chenzeshenga.logistics.model.Product;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Service public class ProductService extends ServiceImpl<ProductMapper, Product> {

    public Page<Product> listByStatusWithUser(Page page, String username, String status) {
        return page.setRecords(baseMapper.listByStatusWithUser(page, username, status));
    }

    public Page<Product> listByStatus(Page page, String status) {
        return page.setRecords(baseMapper.listByStatus(page, status));
    }

}
