package com.abc.chenzeshenga.logistics.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.abc.chenzeshenga.logistics.mapper.WarehousingMapper;
import com.abc.chenzeshenga.logistics.mapper.shelf.UpShelfProductMapper;
import com.abc.chenzeshenga.logistics.model.shelf.UpShelfProduct;
import com.abc.chenzeshenga.logistics.util.SnowflakeIdWorker;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.vo.Json;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author chenzeshenga
 * @since 2020-01-23 23:18
 */
@Slf4j
@RestController
@RequestMapping("/product/shelf")
public class ProductShelfController {

    @Resource
    private UpShelfProductMapper upShelfProductMapper;

    @Resource
    private WarehousingMapper warehousingMapper;

    @PostMapping("/add")
    public Json add(@RequestBody List<UpShelfProduct> upShelfProductList, @RequestParam String warehousingNo) {
        upShelfProductList.forEach(upshelfProduct -> {
            upshelfProduct.setUuid(SnowflakeIdWorker.generateStrId());
            upshelfProduct.setUptime(new Date());
            upShelfProductMapper.insert(upshelfProduct);
        });
        warehousingMapper.statusUpdate(warehousingNo, "4", "5", UserUtils.getUserName(), new Date());
        return Json.succ();
    }

}