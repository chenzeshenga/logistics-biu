package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.ProductMapper;
import com.abc.chenzeshenga.logistics.mapper.ProductStatisticsMapper;
import com.abc.chenzeshenga.logistics.mapper.shelf.UpShelfProductMapper;
import com.abc.chenzeshenga.logistics.mapper.warehouse.ProductOutWarehouseMapper;
import com.abc.chenzeshenga.logistics.model.ProductStatistics;
import com.abc.chenzeshenga.logistics.model.common.PageQueryEntity;
import com.abc.chenzeshenga.logistics.model.common.Pagination;
import com.abc.chenzeshenga.logistics.model.v2.statistics.ProductInWarehouseStatisticsReq;
import com.abc.chenzeshenga.logistics.model.warehouse.ProductInWarehouseSummary;
import com.abc.chenzeshenga.logistics.service.ProductStatisticsService;
import com.abc.chenzeshenga.logistics.service.statistics.ProductInWarehouseService;
import com.abc.chenzeshenga.logistics.service.user.UserCommonService;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.util.PageUtils;
import com.abc.vo.Json;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenzeshenga
 * @version 1.0
 * @since 20190809
 */
@RestController
@RequestMapping("/statistics")
public class ProductStatisticsController {

    @Resource
    private ProductStatisticsMapper productStatisticsMapper;

    @Resource
    private UpShelfProductMapper upShelfProductMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductOutWarehouseMapper productOutWarehouseMapper;

    private final ProductStatisticsService productStatisticsService;

    private final ProductInWarehouseService productInWarehouseService;

    private final UserCommonService userCommonService;

    @Autowired
    public ProductStatisticsController(
            ProductStatisticsService productStatisticsService,
            UserCommonService userCommonService,
            ProductInWarehouseService productInWarehouseService) {
        this.productStatisticsService = productStatisticsService;
        this.userCommonService = userCommonService;
        this.productInWarehouseService = productInWarehouseService;
    }

    @PostMapping("/product/list")
    public Json listProductStatistics(@RequestBody PageQueryEntity<ProductInWarehouseStatisticsReq> productInWarehouseStatisticsReqPageQueryEntity) {
        return Json.succ().data(productStatisticsService.listProductStatistics(productInWarehouseStatisticsReqPageQueryEntity));
    }

    @PostMapping
    @RequestMapping("/list")
    public Json list(@RequestBody String body) {
        String username = UserUtils.getUserName();
        JSONObject jsonObject = JSON.parseObject(body);
        Page page = PageUtils.getPageParam(jsonObject);
        List<ProductStatistics> productStatisticsList;
        Page<ProductStatistics> productStatisticsPage = new Page<>();
        if ("admin".equals(username)) {
            productStatisticsPage = productStatisticsService.selectAll(page);
        } else {
            productStatisticsList = productStatisticsMapper.selectAllByUsername(username);
        }
        return Json.succ().data("page", productStatisticsPage);
    }

    @GetMapping("trigger")
    public Json triggerStatistics() {
        productStatisticsService.triggerStatistics();
        return Json.succ();
    }

    @PostMapping("/listBySearch")
    public Json listBySearch(@RequestBody String body) {
        String username = UserUtils.getUserName();
        JSONObject jsonObject = JSON.parseObject(body);
        String sku = jsonObject.getString("sku");
        String name = jsonObject.getString("name");
        String owner = jsonObject.getString("owner");
        Page page = PageUtils.getPageParam(jsonObject);
        List<ProductStatistics> productStatisticsList;
        Page<ProductStatistics> productStatisticsPage = new Page<>();
        if ("admin".equals(username)) {
            productStatisticsPage = productStatisticsService.selectAllBySearch(page, sku, name, owner);
        } else {
            productStatisticsList = productStatisticsMapper.selectAllByUsername(username);
        }
        return Json.succ().data("page", productStatisticsPage);
    }

    @PostMapping("/productInWarehouse")
    public Json searchProductInWarehouse(
            @RequestBody
                    PageQueryEntity<ProductInWarehouseSummary> productInWarehouseSummaryPageQueryEntity) {
        Pagination pagination = productInWarehouseSummaryPageQueryEntity.getPagination();
        long current = pagination.getCurrent();
        int size = pagination.getSize();
        long fromIdx = (current - 1) * size;
        ProductInWarehouseSummary productInWarehouseSummaryReq =
                productInWarehouseSummaryPageQueryEntity.getEntity();
        String owner = productInWarehouseSummaryReq.getOwner();
        String username = UserUtils.getUserName();
        boolean isManager = userCommonService.isManagerRole(username);
        List<ProductInWarehouseSummary> productInWarehouseSummaries;
        long total;
        if (isManager) {
            if (StringUtils.isEmpty(owner)) {
                productInWarehouseSummaries =
                        productInWarehouseService.fetchProductInWarehouseWithManagerRole(
                                productInWarehouseSummaryReq.getDySku(),
                                productInWarehouseSummaryReq.getName(),
                                owner,
                                fromIdx,
                                size);
                total =
                        productInWarehouseService.countProductInWarehouseWithManagerRole(
                                productInWarehouseSummaryReq.getDySku(),
                                productInWarehouseSummaryReq.getName(),
                                owner);
            } else {
                productInWarehouseSummaries =
                        productInWarehouseService.fetchProductInWarehouseWithUserRole(
                                productInWarehouseSummaryReq.getDySku(),
                                productInWarehouseSummaryReq.getName(),
                                owner,
                                fromIdx,
                                size);
                total =
                        productInWarehouseService.countProductInWarehouseWithUserRole(
                                productInWarehouseSummaryReq.getDySku(),
                                productInWarehouseSummaryReq.getName(),
                                owner);
            }
        } else {
            productInWarehouseSummaries =
                    productInWarehouseService.fetchProductInWarehouseWithUserRole(
                            productInWarehouseSummaryReq.getDySku(),
                            productInWarehouseSummaryReq.getName(),
                            username,
                            fromIdx,
                            size);
            total =
                    productInWarehouseService.countProductInWarehouseWithUserRole(
                            productInWarehouseSummaryReq.getDySku(),
                            productInWarehouseSummaryReq.getName(),
                            username);
        }
        pagination.setTotal(total);
        return Json.succ().data("data", productInWarehouseSummaries).data("page", pagination);
    }

    @GetMapping("/excel/productInWarehouse")
    public void exportProductStatistics(
            HttpServletResponse httpServletResponse,
            @RequestParam(required = false, defaultValue = "") String dySku,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String owner)
            throws IOException {
        String username = UserUtils.getUserName();
        boolean isManager = userCommonService.isManagerRole(username);
        List<ProductInWarehouseSummary> productInWarehouseSummaries;
        if (isManager) {
            if (StringUtils.isEmpty(owner)) {
                productInWarehouseSummaries =
                        productInWarehouseService.fetchAllProductInWarehouseWithManagerRole(dySku, name, owner);
            } else {
                productInWarehouseSummaries =
                        productInWarehouseService.fetchAllProductInWarehouseWithUserRole(dySku, name, owner);
            }
        } else {
            productInWarehouseSummaries =
                    productInWarehouseService.fetchAllProductInWarehouseWithUserRole(dySku, name, username);
        }
        String fileName = "商品在库统计信息.xlsx";
        httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        httpServletResponse.setHeader(
                "Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        writeServletResp(
                httpServletResponse, productInWarehouseSummaries, ProductInWarehouseSummary.class);
    }

    private void writeServletResp(
            HttpServletResponse httpServletResponse,
            List<? extends BaseRowModel> baseRowModels,
            Class<? extends BaseRowModel> clazz)
            throws IOException {
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        ExcelWriter excelWriter = new ExcelWriter(servletOutputStream, ExcelTypeEnum.XLSX);
        Sheet sheet1 = new Sheet(1, 0, clazz);
        excelWriter.write(baseRowModels, sheet1);
        excelWriter.finish();
        httpServletResponse.flushBuffer();
    }

    //  @PostMapping("/productOutWarehouse")
    //  @SuppressWarnings("rawtypes")
    //  public Json searchProductOutWarehouse(@RequestBody String body) {
    //    JSONObject jsonObject = JSON.parseObject(body);
    //    String sku = jsonObject.getString("sku");
    //    String name = jsonObject.getString("name");
    //    String owner = jsonObject.getString("owner");
    //    Page page = PageUtils.getPageParam(jsonObject);
    //    String username = UserUtils.getUserName();
    //    boolean isManager = userCommonService.isManagerRole(username);
    //    List<ProductInWarehouseSummary> productInWarehouseSummaries;
    //    if (isManager) {
    //      if (StringUtils.isEmpty(owner)) {
    //        productInWarehouseSummaries =
    //            productInWarehouseService.fetchProductInWarehouseWithManagerRole(
    //                page, sku, name, owner);
    //      } else {
    //        productInWarehouseSummaries =
    //            productInWarehouseService.fetchProductInWarehouseWithUserRole(page, sku, name,
    // owner);
    //      }
    //    } else {
    //      productInWarehouseSummaries =
    //          productInWarehouseService.fetchProductInWarehouseWithUserRole(page, sku, name,
    // username);
    //    }
    //    productInWarehouseSummaries.forEach(
    //        productInWarehouseSummary -> {
    //          String subSku = productInWarehouseSummary.getDySku();
    //          String subOwner = productInWarehouseSummary.getOwner();
    //          ProductOutWarehouse productOutWarehouse = new ProductOutWarehouse();
    //          productOutWarehouse.setDySku(subSku);
    //          productOutWarehouse.setOwner(subOwner);
    //          List<ProductOutWarehouse> productOutWarehouseList =
    //              productOutWarehouseMapper.list(productOutWarehouse);
    //          productInWarehouseSummary.setProductOutWarehouseList(productOutWarehouseList);
    //        });
    //    return Json.succ().data("data", productInWarehouseSummaries);
    //  }
}
