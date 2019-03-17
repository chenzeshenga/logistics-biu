package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.cache.LabelCache;
import com.abc.chenzeshenga.logistics.mapper.TrackNoMapper;
import com.abc.chenzeshenga.logistics.model.TrackNo;
import com.abc.chenzeshenga.logistics.service.TrackNoService;
import com.abc.util.PageUtils;
import com.abc.vo.Json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Slf4j @RestController @RequestMapping("/trackno") public class TrackNoController {

    @Resource private TrackNoMapper trackNoMapper;

    private TrackNoService trackNoService;

    private LabelCache labelCache;

    @Autowired public TrackNoController(TrackNoService trackNoService, LabelCache labelCache) {
        this.trackNoService = trackNoService;
        this.labelCache = labelCache;
    }

    @PostMapping @RequestMapping("/list") public Json list(@RequestBody String body) {
        JSONObject jsonObject = JSON.parseObject(body);
        Page page = PageUtils.getPageParam(jsonObject);
        Page<TrackNo> trackNoPage = trackNoService.list(page);
        List<TrackNo> trackNoList = trackNoPage.getRecords();
        trackNoList.forEach(trackNo -> trackNo.setCarrierDesc(labelCache.getLabel("carrier_" + trackNo.getCarrier())));
        return Json.succ().data("page", trackNoPage);
    }

    @PostMapping @RequestMapping("/list/{carrierNo}") public Json carrierNo(@RequestBody String body, @PathVariable String carrierNo) {
        JSONObject jsonObject = JSON.parseObject(body);
        Page page = PageUtils.getPageParam(jsonObject);
        Page<TrackNo> trackNoPage = trackNoService.carrierNo(page, carrierNo.replace("carrier_", ""));
        List<TrackNo> trackNoList = trackNoPage.getRecords();
        trackNoList.forEach(trackNo -> trackNo.setCarrierDesc(labelCache.getLabel("carrier_" + trackNo.getCarrier())));
        return Json.succ().data("page", trackNoPage);
    }

    @PostMapping @RequestMapping("/add") public Json add(@RequestBody TrackNo trackNo) {
        trackNo.setCarrier(trackNo.getCarrier().replace("carrier_", ""));
        trackNoMapper.add(trackNo);
        return Json.succ();
    }

    @PostMapping @RequestMapping("/update") public Json update(@RequestBody TrackNo trackNo) {
        trackNo.setCarrier(trackNo.getCarrier().replace("carrier_", ""));
        trackNoMapper.update(trackNo);
        return Json.succ();
    }

    @GetMapping @RequestMapping("/del/{id}") public Json del(@PathVariable String id) {
        trackNoMapper.delete(Integer.valueOf(id));
        return Json.succ();
    }

    @GetMapping @RequestMapping("/pk") public Json generate() {
        TrackNo trackNo = trackNoMapper.generate();
        Long minTrackno = trackNo.getMin();
        if (minTrackno + 1 > trackNo.getMax()) {
            trackNoMapper.delete(trackNo.getId());
        } else {
            trackNo.setMin(minTrackno + 1);
            trackNoMapper.update(trackNo);
        }
        return Json.succ().data(trackNo.getMin());
    }

    @GetMapping @RequestMapping("/pk/{carrier}") public Json generateByCarrier(@PathVariable String carrier) {
        carrier = carrier.replace("carrier_", "");
        TrackNo trackNo = trackNoMapper.generate2(carrier);
        Long minTrackno = trackNo.getMin();
        if (minTrackno + 1 > trackNo.getMax()) {
            trackNoMapper.delete(trackNo.getId());
        } else {
            trackNo.setMin(minTrackno + 1);
            trackNoMapper.update(trackNo);
        }
        return Json.succ().data(trackNo.getMin());
    }

}
