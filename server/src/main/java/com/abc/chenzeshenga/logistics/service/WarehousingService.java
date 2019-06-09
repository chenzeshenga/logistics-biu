package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.mapper.WarehousingMapper;
import com.abc.chenzeshenga.logistics.model.Warehousing;
import com.abc.chenzeshenga.logistics.model.WarehousingReq;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Service public class WarehousingService extends ServiceImpl<WarehousingMapper, Warehousing> {

    public Page<Warehousing> listAll(Page page, String method) {
        return page.setRecords(baseMapper.listAll(page, method));
    }

    public Page<Warehousing> listByOwner(Page page, String creator, String method) {
        return page.setRecords(baseMapper.listByOwner(page, creator, method));
    }

    public Page<Warehousing> listByOwnerAndStatus(Page page, String creator, String status, String method) {
        return page.setRecords(baseMapper.listByOwnerAndStatus(page, creator, status, method));
    }

    public Page<Warehousing> listByStatus(Page page, String status, String method) {
        return page.setRecords(baseMapper.listByStatus(page, status, method));
    }

    public Page<Warehousing> listByOwnerAndStatusAndFilter(Page page, String creator, String status, String method,
        WarehousingReq warehousingReq) {
        return page.setRecords(baseMapper.listByOwnerAndStatusAndFilter(page, creator, status, method, warehousingReq));
    }

    public Page<Warehousing> listByStatusAndFilter(Page page, String status, String method,
        WarehousingReq warehousingReq) {
        return page.setRecords(baseMapper.listByStatusAndFilter(page, status, method, warehousingReq));
    }

}
