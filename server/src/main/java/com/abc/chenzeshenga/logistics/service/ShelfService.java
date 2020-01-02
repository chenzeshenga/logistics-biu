package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.mapper.shelf.ShelfMapper;
import com.abc.chenzeshenga.logistics.model.shelf.Shelf;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @since 2020-01-03 0:31
 */
@Service
public class ShelfService extends ServiceImpl<ShelfMapper, Shelf> {

    public Page<Shelf> list(Page page, String reg) {
        return page.setRecords(baseMapper.list(reg, page));
    }

}