package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.ProductMapper;
import com.abc.chenzeshenga.logistics.model.SkuLabel;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.vo.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@RestController @RequestMapping("/product") @Slf4j public class ProductController {

    @Resource private ProductMapper productMapper;

    @GetMapping("/list") public Json listProduct() {
        String uname = UserUtils.getUserName();
        List<SkuLabel> skuLabelList;
        if ("admin".equals(uname)) {
            skuLabelList = productMapper.listAll();
        } else {
            skuLabelList = productMapper.list(uname);
        }
        skuLabelList.forEach(skuLabel -> {
            skuLabel.setValue(skuLabel.getSku() + "/" + skuLabel.getDySku());
            skuLabel.setLabel(skuLabel.getName() + "(" + skuLabel.getPrice() + ")");
        });
        return Json.succ().data(skuLabelList);
    }

}
