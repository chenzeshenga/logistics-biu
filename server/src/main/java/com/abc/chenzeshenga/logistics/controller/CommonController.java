package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.util.CommonUtil;
import com.abc.chenzeshenga.logistics.util.SkuUtil;
import com.abc.vo.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController @Slf4j public class CommonController {

    @GetMapping("/generate/pk") public Json getOrderNo() {
        String pk = CommonUtil.generate();
        return Json.succ().data(pk);
    }

    @GetMapping("/generate/sku") public Json getDySku() {
        String sku = SkuUtil.generateDySku();
        return Json.succ().data(sku);
    }

    @GetMapping("/ord/excel/{ordno}") public void getOrdExcel(@PathVariable String ordno) {

    }

    @GetMapping("/ord/csv/{ordno}") public void getOrdCsv(@PathVariable String ordno) {

    }

    @GetMapping("/warehousing/excel/{ordno}") public void getWarehousingExcel(@PathVariable String ordno) {

    }

    @GetMapping("/warehousing/csv/{ordno}") public void getWarehousingCsv(@PathVariable String ordno) {

    }

}
