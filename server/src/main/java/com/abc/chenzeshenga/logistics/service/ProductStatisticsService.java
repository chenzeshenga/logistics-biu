package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.mapper.ProductStatisticsMapper;
import com.abc.chenzeshenga.logistics.model.ProductStatistics;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @version 1.0
 * @since 20190809
 */
@Service public class ProductStatisticsService extends ServiceImpl<ProductStatisticsMapper, ProductStatistics> {

    public Page<ProductStatistics> selectAll(Page page) {
        return page.setRecords(baseMapper.selectAll(page));
    }
}
