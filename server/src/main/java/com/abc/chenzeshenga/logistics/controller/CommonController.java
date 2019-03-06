package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.util.CommonUtil;
import com.abc.vo.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @Slf4j public class CommonController {

    @GetMapping("/generate/pk") public Json getOrderNo() {
        String pk = CommonUtil.generate();
        return Json.succ().data(pk);
    }

}
