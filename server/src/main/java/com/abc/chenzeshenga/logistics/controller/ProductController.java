package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.cache.LabelCache;
import com.abc.chenzeshenga.logistics.mapper.ImgMapper;
import com.abc.chenzeshenga.logistics.mapper.ProductMapper;
import com.abc.chenzeshenga.logistics.model.Product;
import com.abc.chenzeshenga.logistics.model.SkuLabel;
import com.abc.chenzeshenga.logistics.model.common.PageQueryEntity;
import com.abc.chenzeshenga.logistics.model.common.Pagination;
import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.abc.chenzeshenga.logistics.service.ProductService;
import com.abc.chenzeshenga.logistics.util.SkuUtil;
import com.abc.chenzeshenga.logistics.util.SqlUtils;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.vo.Json;

import java.io.IOException;
import java.util.*;
import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    private static final String ADMIN = "admin";

    private static final String ONE = "1";

    private static final String ZERO = "0";

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ImgMapper imgMapper;

    private final ProductService productService;

    private final LabelCache labelCache;

    private final CommonController commonController;

    @Autowired
    public ProductController(
            ProductService productService, LabelCache labelCache, CommonController commonController) {
        this.productService = productService;
        this.labelCache = labelCache;
        this.commonController = commonController;
    }

    @GetMapping("/list")
    public Json listProduct() {
        String uname = UserUtils.getUserName();
        List<SkuLabel> skuLabelList;
        skuLabelList = productMapper.list(uname);
        skuLabelList.forEach(
                skuLabel -> {
                    skuLabel.setValue(skuLabel.getSku() + "/" + skuLabel.getDySku());
                    skuLabel.setLabel(skuLabel.getName() + "(" + skuLabel.getValue() + ")");
                });
        return Json.succ().data(skuLabelList);
    }

    @PostMapping(value = "/add")
    public Json add(@RequestBody @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errMsg = getErrMsg(bindingResult);
            return Json.fail().msg(errMsg.toString());
        }
        String username = UserUtils.getUserName();
        String creator = product.getCreatedBy();
        if (StringUtils.isNoneBlank(creator)) {
            product.setCreatedBy(creator);
        } else {
            product.setCreatedBy(username);
        }
        Date curr = new Date();
        Product ori = productMapper.selectByPrimaryKey(product.getSku());
        if (ori == null) {
            product.setDySku(SkuUtil.generateDySku());
            product.setUpdateBy(username);
            product.setCreateOn(curr);
            product.setUpdateOn(curr);
            product.setStatus("0");
            productMapper.insert(product);
        } else if (StringUtils.isNoneBlank(ori.getDySku())) {
            BeanUtils.copyProperties(product, ori);
            product.setCreatedBy(product.getCreatedBy());
            product.setUpdateBy(username);
            product.setCreateOn(curr);
            product.setUpdateOn(curr);
            product.setStatus("0");
            productMapper.updateByPrimaryKeySelectiveV2(product);
        }
        return Json.succ();
    }

    @PostMapping("/list/{status}")
    public Json listProduct(
            @RequestBody PageQueryEntity<Product> productPageQueryEntity, @PathVariable String status) {
        String username = UserUtils.getUserName();
        Product searchProduct = productPageQueryEntity.getEntity();
        searchProduct.setStatus(status);
        Pagination pagination = productPageQueryEntity.getPagination();
        SqlLimit sqlLimit = SqlUtils.generateSqlLimitV2(pagination);
        List<Product> productList;
        if (ADMIN.equals(username)) {
            productList = productService.listByStatus(searchProduct, sqlLimit);
        } else {
            if (StringUtils.isNotBlank(searchProduct.getCreatedBy())) {
                productList = productService.listByStatusWithUser(searchProduct, sqlLimit);
            } else {
                searchProduct.setCreatedBy(username);
                productList = productService.listByStatusWithUser(searchProduct, sqlLimit);
            }
        }
        long total = productService.countByStatus(searchProduct);
        pagination.setTotal(total);
        productList.forEach(
                product -> {
                    if (StringUtils.isEmpty(product.getCategoryName())) {
                        product.setCategoryName(labelCache.getLabel("classification_" + product.getCategory()));
                    }
                    if (ZERO.equals(product.getStatus())) {
                        product.setStatusDesc("审核中");
                    } else if (ONE.equals(product.getStatus())) {
                        product.setStatusDesc("在库");
                    }
                });
        return Json.succ().data("page", pagination).data("data", productList);
    }

    private StringBuilder getErrMsg(BindingResult bindingResult) {
        StringBuilder errMsg = new StringBuilder();
        for (ObjectError objectError : bindingResult.getAllErrors()) {
            errMsg.append(objectError.getDefaultMessage()).append(";");
        }
        return errMsg;
    }

    @PostMapping(value = "/img/put")
    public Json putImg(
            @RequestParam(value = "file") MultipartFile multipartFile,
            @RequestParam(value = "sku") String sku)
            throws IOException {
        if (StringUtils.isEmpty(sku)) {
            throw new IllegalArgumentException("商品sku必填");
        }
        Product ori = productMapper.whetherImgsV2(sku);
        String uuid = UUID.randomUUID().toString();
        if (ori != null) {
            ori.setImgPath("http://47.105.33.48:8888/api/v1/img/" + uuid);
            commonController.putImg(multipartFile, uuid);
            ori.setUpdateOn(new Date());
            ori.setUpdateBy(UserUtils.getUserName());
            productMapper.updateByPrimaryKeySelectiveV2(ori);
        } else {
            ori = new Product();
            ori.setSku(sku);
            ori.setDySku(SkuUtil.generateDySku());
            ori.setImgPath("http://47.105.33.48:8888/api/v1/img/" + uuid);
            String username = UserUtils.getUserName();
            Date curr = new Date();
            ori.setUpdateBy(username);
            ori.setCreatedBy(username);
            ori.setCreateOn(curr);
            ori.setUpdateOn(curr);
            commonController.putImg(multipartFile, uuid);
            productMapper.insert(ori);
        }
        return Json.succ().data(ori);
    }

    @GetMapping(value = "/img/drop/{uuid}/{sku}/{index}")
    public Json putImg(
            @PathVariable String uuid, @PathVariable String sku, @PathVariable String index) {
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

    @GetMapping("/delete/{sku}")
    public Json delete(@PathVariable String sku) {
        productMapper.deleteByPrimaryKey(sku);
        return Json.succ();
    }

    @GetMapping("/status/{sku}/{status}")
    public Json statusUpdate(@PathVariable String sku, @PathVariable String status) {
        productMapper.statusUpdate(sku, status);
        return Json.succ();
    }

    @PostMapping("/update")
    public Json update(@RequestBody Product product) {
        String username = UserUtils.getUserName();
        Date curr = new Date();
        product.setUpdateBy(username);
        product.setUpdateOn(curr);
        productMapper.updateByPrimaryKeySelective(product);
        return Json.succ();
    }

    @GetMapping("/listByUser")
    public Json listProductByUser(@RequestParam String username) {
        List<SkuLabel> skuLabelList = productMapper.listUserOwnProduct(username);
        return Json.succ().data(skuLabelList);
    }

    @GetMapping("/get/{sku}")
    public Json getSingleProduct(@PathVariable String sku) {
        Product product = productMapper.selectByPrimaryKey(sku);
        return Json.succ().data(product);
    }

    @PostMapping("/update/approval")
    public Json batchApproval(@RequestBody List<String> skus) {
        productMapper.batchUpdate(skus);
        return Json.succ();
    }

    @PostMapping("/listByUser")
    public Json listByUser(@RequestBody Map<String, String> request) {
        List<SkuLabel> skuLabelList = productMapper.list(request.get("user"));
        return Json.succ().data(skuLabelList);
    }

    @PostMapping("/listAllByUser")
    public Json listAllByUser(@RequestBody Map<String, String> request) {
        List<SkuLabel> skuLabelList = productMapper.listAllByUser(request.get("user"));
        return Json.succ().data(skuLabelList);
    }
}
