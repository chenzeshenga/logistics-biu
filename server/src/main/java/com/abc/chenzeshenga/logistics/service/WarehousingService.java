package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.mapper.WarehousingMapper;
import com.abc.chenzeshenga.logistics.model.Warehousing;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Service public class WarehousingService extends ServiceImpl<WarehousingMapper, Warehousing> {

    public Page<Warehousing> listAll(Page page) {
        return page.setRecords(baseMapper.listAll(page));
    }

    public Page<Warehousing> listByOwner(Page page, String creator) {
        return page.setRecords(baseMapper.listByOwner(page, creator));
    }

    public Page<Warehousing> listByOwnerAndStatus(Page page, String creator, String status) {
        return page.setRecords(baseMapper.listByOwnerAndStatus(page, creator, status));
    }

    public Page<Warehousing> listByStatus(Page page, String status) {
        return page.setRecords(baseMapper.listByStatus(page, status));
    }

}
