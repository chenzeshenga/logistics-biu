package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.ReturnMapper;
import com.abc.chenzeshenga.logistics.model.Product;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.vo.Json;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @author chenzesheng
 * @version 1.0
 * @since 2019.07.19
 */
@RestController @Slf4j @RequestMapping("/return") public class ReturnController {

    @Resource private ReturnMapper returnMapper;

    @PostMapping(value = "/img/put") public Json putImg(@RequestParam(value = "file") MultipartFile multipartFile,
        @RequestParam(value = "returnNo") String sku) throws IOException {
        if (StringUtils.isEmpty(sku)) {
            throw new IllegalArgumentException("商品sku必填");
        }
        Product ori = productMapper.whetherImgsV2(sku);
        String uuid = UUID.randomUUID().toString();
        if (ori != null) {
            if (StringUtils.isEmpty(ori.getImg1())) {
                ori.setImg1(uuid);
                commonController.putImg(multipartFile, uuid);
            } else if (StringUtils.isNotEmpty(ori.getImg1()) && StringUtils.isEmpty(ori.getImg2())) {
                ori.setImg2(uuid);
                commonController.putImg(multipartFile, uuid);
            } else if (StringUtils.isNotEmpty(ori.getImg1()) && StringUtils.isNotEmpty(ori.getImg2()) && StringUtils
                .isEmpty(ori.getImg3())) {
                ori.setImg3(uuid);
                commonController.putImg(multipartFile, uuid);
            }
            ori.setUpdateOn(new Date());
            ori.setUpdateBy(UserUtils.getUserName());
            productMapper.updateByPrimaryKeySelective(ori);
        } else {
            ori = new Product();
            ori.setSku(sku);
            ori.setImg1(uuid);
            String username = UserUtils.getUserName();
            Date curr = new Date();
            ori.setUpdateBy(username);
            ori.setCreatedBy(username);
            ori.setCreateOn(curr);
            ori.setUpdateOn(curr);
            commonController.putImg(multipartFile, uuid);
            productMapper.addImg(ori);
        }
        return Json.succ();
    }
}
