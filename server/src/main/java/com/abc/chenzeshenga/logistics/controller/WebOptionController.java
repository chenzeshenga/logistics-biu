package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.cache.LabelCache;
import com.abc.chenzeshenga.logistics.model.Label;
import com.abc.vo.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@RestController @Slf4j @RequestMapping("/option") public class WebOptionController {

    private LabelCache labelCache;

    @Autowired public WebOptionController(LabelCache labelCache) {
        this.labelCache = labelCache;
    }

    @GetMapping @RequestMapping("/carrier") public Json getCarrier() {
        List<Label> labelList = labelCache.getLabelList("carrier_");
        return Json.succ().data(labelList);
    }

}
