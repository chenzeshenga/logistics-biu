package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.model.Template;
import com.abc.vo.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import com.abc.chenzeshenga.logistics.mapper.TemplateMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenzesheng
 */
@Service @RestController @RequestMapping(path = "/template") @Slf4j public class TemplateController {
    @Resource TemplateMapper mapper;

    @GetMapping @RequestMapping("/list") public Json list() {
        List<Template> templateList = mapper.list();
        templateList.forEach(template -> template.setLink("http://localhost:8888/api/v1/template/file/" + template.getUuid()));
        return Json.succ().data(templateList);
    }

}