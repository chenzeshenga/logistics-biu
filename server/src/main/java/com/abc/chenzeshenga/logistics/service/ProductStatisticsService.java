package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.mapper.ProductStatisticsMapper;
import com.abc.chenzeshenga.logistics.model.CompanyProfile;
import com.abc.chenzeshenga.logistics.model.Product;
import com.abc.chenzeshenga.logistics.model.ProductStatistics;
import com.abc.chenzeshenga.logistics.model.common.PageData;
import com.abc.chenzeshenga.logistics.model.common.PageQueryEntity;
import com.abc.chenzeshenga.logistics.model.common.Pagination;
import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.abc.chenzeshenga.logistics.model.v2.statistics.ProductInWarehouseStatistics;
import com.abc.chenzeshenga.logistics.model.v2.statistics.ProductInWarehouseStatisticsReq;
import com.abc.chenzeshenga.logistics.service.company.CompanyProfileService;
import com.abc.chenzeshenga.logistics.service.dict.IDictService;
import com.abc.chenzeshenga.logistics.service.product.ProductInWarehouseRecordService;
import com.abc.chenzeshenga.logistics.util.DateUtil;
import com.abc.chenzeshenga.logistics.util.SnowflakeIdWorker;
import com.abc.chenzeshenga.logistics.util.SqlUtils;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @version 1.0
 * @since 20190809
 */
@Service
@Slf4j
public class ProductStatisticsService
  extends ServiceImpl<ProductStatisticsMapper, ProductStatistics> {

  private final ProductInWarehouseRecordService productInWarehouseRecordService;

  private final ProductService productService;

  private IDictService dictService;

  private CompanyProfileService companyProfileService;

  private ExecutorService pool;

  @Autowired
  public ProductStatisticsService(
    ProductInWarehouseRecordService productInWarehouseRecordService,
    ProductService productService,
    IDictService dictService,
    CompanyProfileService companyProfileService,
    ExecutorService pool
  ) {
    this.productInWarehouseRecordService = productInWarehouseRecordService;
    this.productService = productService;
    this.dictService = dictService;
    this.companyProfileService = companyProfileService;
    this.pool = pool;
  }

  public Page<ProductStatistics> selectAll(Page page) {
    return page.setRecords(baseMapper.selectAll(page));
  }

  public Page<ProductStatistics> selectAllBySearch(
    Page page, String sku, String name, String owner) {
    return page.setRecords(baseMapper.selectAllBySearch(page, sku, name, owner));
  }

  public int updateHistoryProductStatistics(
    ProductInWarehouseStatistics productInWarehouseStatistics) {
    productInWarehouseStatistics.setUpdateBy(UserUtils.getUserName());
    productInWarehouseStatistics.setUpdateTime(new Date());
    productInWarehouseStatistics.setAdminUpdate(true);
    return baseMapper.updateHistoryProductStatistics(productInWarehouseStatistics);
  }

  @PostConstruct
  @Scheduled(cron = "0 0 0 * * ?")
  public void triggerStatistics() {
    pool.execute(() -> {
      baseMapper.deleteAll();
      int result = baseMapper.deleteCurrDayHistory(DateUtil.getOnlyDateStrFromDate(new Date()));
      log.info("total {} history record deleted", result);
      List<ProductInWarehouseStatistics> productInWarehouseStatisticsList = baseMapper.triggerCount();
      productInWarehouseStatisticsList.forEach(
        productInWarehouseStatistics -> {
          productInWarehouseStatistics.setUuid(SnowflakeIdWorker.generateStrId());
          String dySku = productInWarehouseStatistics.getDySku();
          String currDate = DateUtil.getOnlyDateStrFromDate(new Date());
          ProductInWarehouseStatistics ori = baseMapper.selectHistoryBySkuAndDate(dySku, currDate);
          if (StringUtils.isNotBlank(dySku)) {
            Product product = productService.selectProductByDySku(dySku);
            if (product != null) {
              if (StringUtils.isNotBlank(product.getLength())
                && StringUtils.isNotBlank(product.getWidth())
                && StringUtils.isNotBlank(product.getHeight())) {
                double volume =
                  new BigDecimal(product.getLength())
                    .multiply(new BigDecimal(product.getWidth()))
                    .multiply(new BigDecimal(product.getHeight()))
                    .multiply(new BigDecimal(productInWarehouseStatistics.getTotalNum()))
                    .doubleValue();
                productInWarehouseStatistics.setVolume(volume);
                CompanyProfile companyProfile =
                  companyProfileService.init(productInWarehouseStatistics.getOwner());
                if (companyProfile != null && companyProfile.getCostOnVolume() != null) {
                  BigDecimal costOnPerVolume = companyProfile.getCostOnVolume();
                  Double costOnVolume =
                    BigDecimal.valueOf(volume)
                      .divide(new BigDecimal(100 * 100 * 100), BigDecimal.ROUND_CEILING)
                      .multiply(costOnPerVolume)
                      .doubleValue();
                  productInWarehouseStatistics.setCostOnVolume(costOnVolume);
                }
              }
              if (StringUtils.isNotBlank(product.getWeight())) {
                productInWarehouseStatistics.setWeight(
                  new BigDecimal(productInWarehouseStatistics.getTotalNum())
                    .multiply(new BigDecimal(product.getWeight()))
                    .doubleValue());
              }
            }
            productInWarehouseStatistics.setDate(currDate);
            productInWarehouseStatistics.setUpdateBy("system");
            productInWarehouseStatistics.setUpdateTime(new Date());
            baseMapper.insertProductInWarehouseSingle(productInWarehouseStatistics);
            if (ori == null) {
              baseMapper.insertProductInWarehouseHistorySingle(productInWarehouseStatistics);
            }
          }
        });
    });
  }

  public PageData<ProductInWarehouseStatistics> listProductStatistics(
    PageQueryEntity<ProductInWarehouseStatisticsReq>
      productInWarehouseStatisticsReqPageQueryEntity) {
    Pagination pagination = productInWarehouseStatisticsReqPageQueryEntity.getPagination();
    SqlLimit sqlLimit = SqlUtils.generateSqlLimit(pagination);
    ProductInWarehouseStatisticsReq productInWarehouseStatisticsReq =
      productInWarehouseStatisticsReqPageQueryEntity.getEntity();
    List<ProductInWarehouseStatistics> productInWarehouseStatisticsList =
      baseMapper.select(sqlLimit, productInWarehouseStatisticsReq);
    long total = baseMapper.count(productInWarehouseStatisticsReq);
    PageData<ProductInWarehouseStatistics> productInWarehouseStatisticsPageData = new PageData<>();
    productInWarehouseStatisticsPageData.setData(productInWarehouseStatisticsList);
    productInWarehouseStatisticsPageData.setTotal(total);
    return productInWarehouseStatisticsPageData;
  }

  public PageData<ProductInWarehouseStatistics> listHistoryProductStatistics(
    PageQueryEntity<ProductInWarehouseStatisticsReq>
      productInWarehouseStatisticsReqPageQueryEntity) {
    Pagination pagination = productInWarehouseStatisticsReqPageQueryEntity.getPagination();
    SqlLimit sqlLimit = SqlUtils.generateSqlLimit(pagination);
    ProductInWarehouseStatisticsReq productInWarehouseStatisticsReq =
      productInWarehouseStatisticsReqPageQueryEntity.getEntity();
    List<ProductInWarehouseStatistics> productInWarehouseStatisticsList =
      baseMapper.selectHistory(sqlLimit, productInWarehouseStatisticsReq);
    long total = baseMapper.countHistory(productInWarehouseStatisticsReq);
    PageData<ProductInWarehouseStatistics> productInWarehouseStatisticsPageData = new PageData<>();
    productInWarehouseStatisticsPageData.setData(productInWarehouseStatisticsList);
    productInWarehouseStatisticsPageData.setTotal(total);
    return productInWarehouseStatisticsPageData;
  }
}
