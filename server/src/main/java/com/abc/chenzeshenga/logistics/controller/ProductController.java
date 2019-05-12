package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.cache.LabelCache;
import com.abc.chenzeshenga.logistics.mapper.ImgMapper;
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
import java.util.*;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@RestController @RequestMapping("/product") @Slf4j public class ProductController {

    private static final String ADMIN = "admin";

    private static final String ONE = "1";

    private static final String ZERO = "0";

    @Resource private ProductMapper productMapper;

    @Resource private ImgMapper imgMapper;

    private ProductService productService;

    private LabelCache labelCache;

    private CommonController commonController;

    @Autowired
    public ProductController(ProductService productService, LabelCache labelCache, CommonController commonController) {
        this.productService = productService;
        this.labelCache = labelCache;
        this.commonController = commonController;
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
            skuLabel.setLabel(skuLabel.getName() + "(" + skuLabel.getValue() + ")");
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
            product.setStatus("0");
            productMapper.insert(product);
        } else if (StringUtils.isEmpty(ori.getProductName())) {
            product.setDySku(SkuUtil.generateDySku());
            product.setCreatedBy(username);
            product.setUpdateBy(username);
            product.setCreateOn(curr);
            product.setUpdateOn(curr);
            product.setStatus("0");
            productMapper.updateByPrimaryKeySelective(product);
        } else {
            product.setUpdateBy(username);
            product.setUpdateOn(curr);
            product.setStatus("0");
            productMapper.updateByPrimaryKeySelective(product);
        }
        return Json.succ();
    }

    @PostMapping @RequestMapping("/list/{status}")
    public Json listProduct(@RequestBody String body, @PathVariable String status) {
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
        productList.forEach(product -> {
            if (StringUtils.isEmpty(product.getCategoryName())) {
                product.setCategoryName(labelCache.getLabel("classification_" + product.getCategory()));
            }
            if (ZERO.equals(product.getStatus())) {
                product.setStatusDesc("审核中");
            } else if (ONE.equals(product.getStatus())) {
                product.setStatusDesc("在库");
            }
        });
        return Json.succ().data("page", productPage);
    }

    private StringBuilder getErrMsg(BindingResult bindingResult) {
        StringBuilder errMsg = new StringBuilder();
        for (ObjectError objectError : bindingResult.getAllErrors()) {
            errMsg.append(objectError.getDefaultMessage()).append(";");
        }
        return errMsg;
    }

    @PostMapping(value = "/img/put") public Json putImg(@RequestParam(value = "file") MultipartFile multipartFile,
        @RequestParam(value = "sku") String sku) throws IOException {
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

    @GetMapping(value = "/img/drop/{uuid}/{sku}/{index}")
    public Json putImg(@PathVariable String uuid, @PathVariable String sku, @PathVariable String index) {
        Product product = new Product();
        product.setSku(sku);
        switch (index) {
            case "1":
                product.setImg1("");
                break;
            case "2":
                product.setImg2("");
                break;
            case "3":
                product.setImg3("");
                break;
            default:
                break;
        }
        product.setUpdateBy(UserUtils.getUserName());
        product.setUpdateOn(new Date());
        productMapper.updateByPrimaryKeySelective(product);
        imgMapper.deleteByPrimaryKey(uuid);
        return Json.succ();
    }

    @GetMapping @RequestMapping("/delete/{sku}") public Json delete(@PathVariable String sku) {
        productMapper.deleteByPrimaryKey(sku);
        return Json.succ();
    }

    @GetMapping @RequestMapping("/status/{sku}/{status}")
    public Json statusUpdate(@PathVariable String sku, @PathVariable String status) {
        productMapper.statusUpdate(sku, status);
        return Json.succ();
    }

    @PostMapping @RequestMapping("/update") public Json update(@RequestBody Product product) {
        String username = UserUtils.getUserName();
        Date curr = new Date();
        product.setUpdateBy(username);
        product.setUpdateOn(curr);
        productMapper.updateByPrimaryKeySelective(product);
        return Json.succ();
    }

    @GetMapping @RequestMapping("/get/{sku}") public Json getSingleProduct(@PathVariable String sku) {
        Product product = productMapper.selectByPrimaryKey(sku);
        return Json.succ().data(product);
    }

    @PostMapping @RequestMapping("/update/approval") public Json batchApproval(@RequestBody List<String> skus) {
        productMapper.batchUpdate(skus);
        return Json.succ();
    }

    @PostMapping @RequestMapping("/listByUser") public Json listByUser(@RequestBody Map<String, String> request) {
        List<SkuLabel> skuLabelList = productMapper.list(request.get("user"));
        skuLabelList.forEach(skuLabel -> {
            skuLabel.setValue(skuLabel.getSku() + "/" + skuLabel.getDySku());
            skuLabel.setLabel(skuLabel.getName() + "(" + skuLabel.getValue() + ")");
        });
        return Json.succ().data(skuLabelList);
    }

}
