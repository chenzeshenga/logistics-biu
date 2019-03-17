package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.mapper.TrackNoMapper;
import com.abc.chenzeshenga.logistics.model.TrackNo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Service public class TrackNoService extends ServiceImpl<TrackNoMapper, TrackNo> {

    public Page<TrackNo> list(Page page) {
        return page.setRecords(baseMapper.list(page));
    }

    public Page<TrackNo> carrierNo(Page page, String carrierNo) {
        return page.setRecords(baseMapper.carrierNo(page, carrierNo));
    }

}
