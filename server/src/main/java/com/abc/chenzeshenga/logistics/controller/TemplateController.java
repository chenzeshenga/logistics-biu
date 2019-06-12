package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.model.CommonObj;
import com.abc.chenzeshenga.logistics.model.Template;
import com.abc.chenzeshenga.logistics.service.TemplateService;
import com.abc.util.PageUtils;
import com.abc.vo.Json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenzesheng
 */
@Service @RestController @RequestMapping(path = "/template") @Slf4j public class TemplateController {

    private TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping @RequestMapping("/list")
    public Json list(@RequestBody String body, @RequestParam(required = false) String reg) {
        JSONObject jsonObject = JSON.parseObject(body);
        Page page = PageUtils.getPageParam(jsonObject);
        reg = "null".equals(reg) ? "" : reg;
        Page<Template> templatePage = templateService.list(page, new CommonObj(reg));
        return Json.succ().data("page", templatePage);
    }

}
