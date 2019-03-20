package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.ProductMapper;
import com.abc.chenzeshenga.logistics.model.Product;
import com.abc.chenzeshenga.logistics.model.SkuLabel;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.vo.Json;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
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

    @PostMapping(value = "/add") public Json add(@RequestBody @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errMsg = new StringBuilder();
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                errMsg.append(objectError.getDefaultMessage()).append(";");
            }
            return Json.fail().msg(errMsg.toString());
        }
        productMapper.add(product);
        return Json.succ();
    }

    @PostMapping(value = "/img")
    public Json addImg(@RequestParam(value = "file") MultipartFile multipartFile, @RequestParam(value = "sku") String sku) throws IOException {
        if (StringUtils.isEmpty(sku)) {
            throw new IllegalArgumentException("产品sku必填");
        }
        Product ori = productMapper.whetherImgs(sku);
        if (ori != null) {
            if (ori.getImg1() == null) {
                ori.setImg1(multipartFile.getBytes());
            } else if (ori.getImg1() != null && ori.getImg2() == null) {
                ori.setImg2(multipartFile.getBytes());
            } else if (ori.getImg1() != null && ori.getImg2() != null && ori.getImg3() == null) {
                ori.setImg3(multipartFile.getBytes());
            }
        } else {
            ori = new Product();
            ori.setSku(sku);
            ori.setImg1(multipartFile.getBytes());
        }
        productMapper.addImg(ori);
        return Json.succ();
    }

    //    @GetMapping @RequestMapping(value = "/listWithImg") public Json listWithImg() {
    //        Product product = productMapper.listWithImg();
    //        return Json.succ().data(product);
    //    }

}
