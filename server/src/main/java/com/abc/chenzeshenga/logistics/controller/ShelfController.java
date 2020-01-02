package com.abc.chenzeshenga.logistics.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.abc.chenzeshenga.logistics.mapper.shelf.ShelfMapper;
import com.abc.chenzeshenga.logistics.model.shelf.Shelf;
import com.abc.chenzeshenga.logistics.service.ShelfService;
import com.abc.util.PageUtils;
import com.abc.vo.Json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenzeshenga
 * @since 2020-01-01 23:42
 * 
 */
@RestController
@RequestMapping("/shelf")
public class ShelfController {

    @Resource
    private ShelfMapper shelfMapper;

    private ShelfService shelfService;

    @Autowired
    public ShelfController(ShelfService shelfService) {
        this.shelfService = shelfService;
    }

    @PostMapping("/list")
    public Json list(@RequestParam String reg, @RequestBody String body) {
        JSONObject jsonObject = JSON.parseObject(body);
        Page page = PageUtils.getPageParam(jsonObject);
        Page<Shelf> shelfPage = shelfService.list(page, reg);
        return Json.succ().data("page", shelfPage);
    }

}