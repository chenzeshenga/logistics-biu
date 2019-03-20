package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.ProductMapper;
import com.abc.chenzeshenga.logistics.model.Product;
import com.abc.chenzeshenga.logistics.model.SkuLabel;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.vo.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
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

    @PostMapping(value = "/img")
    public Json addImg(@RequestParam(value = "file") MultipartFile multipartFile, @RequestParam(value = "tmp") String tmp) throws IOException {
        //        InputStream inputStream = multipartFile.getInputStream();
        Product product = new Product();
        product.setSku(String.valueOf(Math.random()));
        product.setImg(multipartFile.getBytes());
        productMapper.addImg(product);
        return Json.succ();
    }

    @GetMapping @RequestMapping(value = "/listWithImg") public Json listWithImg() {
        Product product = productMapper.listWithImg();
        return Json.succ().data(product);
    }

}
