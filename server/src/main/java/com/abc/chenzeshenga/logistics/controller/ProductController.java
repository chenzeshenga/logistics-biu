package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.cache.LabelCache;
import com.abc.chenzeshenga.logistics.mapper.ProductMapper;
import com.abc.chenzeshenga.logistics.model.Product;
import com.abc.chenzeshenga.logistics.model.SkuLabel;
import com.abc.chenzeshenga.logistics.service.ProductService;
import com.abc.chenzeshenga.logistics.util.SkuUtil;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.util.PageUtils;
import com.abc.vo.Json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@RestController @RequestMapping("/product") @Slf4j public class ProductController {

    private static final String ADMIN = "admin";

    @Resource private ProductMapper productMapper;

    private ProductService productService;

    private LabelCache labelCache;

    @Autowired public ProductController(ProductService productService, LabelCache labelCache) {
        this.productService = productService;
        this.labelCache = labelCache;
    }

    @GetMapping("/list") public Json listProduct() {
        String uname = UserUtils.getUserName();
        List<SkuLabel> skuLabelList;
        if (ADMIN.equals(uname)) {
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
            StringBuilder errMsg = getErrMsg(bindingResult);
            return Json.fail().msg(errMsg.toString());
        }
        String username = UserUtils.getUserName();
        Date curr = new Date();
        Product ori = productMapper.selectByPrimaryKey(product.getSku());
        if (ori == null) {
            product.setDySku(SkuUtil.generateDySku());
            product.setCreatedBy(username);
            product.setUpdateBy(username);
            product.setCreateOn(curr);
            product.setUpdateOn(curr);
            productMapper.insert(product);
        } else {
            product.setUpdateBy(username);
            product.setUpdateOn(curr);
            productMapper.updateByPrimaryKey(product);
        }
        return Json.succ();
    }

    @PostMapping @RequestMapping("/list/{status}") public Json listProduct(@RequestBody String body, @PathVariable String status) {
        String username = UserUtils.getUserName();
        JSONObject jsonObject = JSON.parseObject(body);
        Page page = PageUtils.getPageParam(jsonObject);
        Page<Product> productPage;
        if (ADMIN.equals(username)) {
            productPage = productService.listByStatus(page, status);
        } else {
            productPage = productService.listByStatusWithUser(page, username, status);
        }
        List<Product> productList = productPage.getRecords();
        productList.forEach(product -> product.setCategoryName(labelCache.getLabel("classification_" + product.getCategory())));
        return Json.succ().data("page", productPage);
    }

    private StringBuilder getErrMsg(BindingResult bindingResult) {
        StringBuilder errMsg = new StringBuilder();
        for (ObjectError objectError : bindingResult.getAllErrors()) {
            errMsg.append(objectError.getDefaultMessage()).append(";");
        }
        return errMsg;
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

}
