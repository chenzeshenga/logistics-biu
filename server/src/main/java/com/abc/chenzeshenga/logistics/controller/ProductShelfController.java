package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.FileMapper;
import com.abc.chenzeshenga.logistics.mapper.ProductMapper;
import com.abc.chenzeshenga.logistics.mapper.UserFileRecordMapper;
import com.abc.chenzeshenga.logistics.mapper.WarehousingMapper;
import com.abc.chenzeshenga.logistics.mapper.shelf.UpShelfProductMapper;
import com.abc.chenzeshenga.logistics.mapper.warehouse.ProductInWarehouseRecordMapper;
import com.abc.chenzeshenga.logistics.model.File;
import com.abc.chenzeshenga.logistics.model.Product;
import com.abc.chenzeshenga.logistics.model.UserFileRecord;
import com.abc.chenzeshenga.logistics.model.Warehousing;
import com.abc.chenzeshenga.logistics.model.shelf.ShelfContent;
import com.abc.chenzeshenga.logistics.model.shelf.UpShelfProduct;
import com.abc.chenzeshenga.logistics.model.shelf.UpShelfProductInFile;
import com.abc.chenzeshenga.logistics.model.warehouse.ProductInWarehouse;
import com.abc.chenzeshenga.logistics.util.DateUtil;
import com.abc.chenzeshenga.logistics.util.SnowflakeIdWorker;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.vo.Json;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenzeshenga
 * @since 2020-01-23 23:18
 */
@Slf4j
@RestController
@RequestMapping("/product/shelf")
public class ProductShelfController {

  @Resource private UpShelfProductMapper upShelfProductMapper;
  @Resource private ProductInWarehouseRecordMapper productInWarehouseRecordMapper;
  @Resource private FileMapper fileMapper;
  @Resource private WarehousingMapper warehousingMapper;
  @Resource private UserFileRecordMapper userFileRecordMapper;
  @Resource private ProductMapper productMapper;

  @PostMapping("/add")
  public Json add(
      @RequestBody List<UpShelfProduct> upShelfProductList, @RequestParam String warehousingNo) {
    Warehousing warehousing = warehousingMapper.selectById(warehousingNo);
    upShelfProductList.forEach(
        upshelfProduct -> {
          upshelfProduct.setUuid(SnowflakeIdWorker.generateStrId());
          upshelfProduct.setUptime(new Date());
          upshelfProduct.setWarehousingNo(warehousingNo);
          upshelfProduct.setOwner(warehousing.getCreator());
          upShelfProductMapper.insert(upshelfProduct);
          ProductInWarehouse productInWarehouse = new ProductInWarehouse();
          productInWarehouse.setUuid(SnowflakeIdWorker.generateStrId());
          productInWarehouse.setDySku(upshelfProduct.getDySku());
          productInWarehouse.setNum(upshelfProduct.getNum());
          productInWarehouse.setOwner(upshelfProduct.getOwner());
          productInWarehouse.setWarehousingNo(upshelfProduct.getWarehousingNo());
          productInWarehouse.setInTime(new Date());
          productInWarehouse.setShelfNo(upshelfProduct.getShelfNo());
          productInWarehouse.setWarehousingNo(upshelfProduct.getWarehousingNo());
          productInWarehouseRecordMapper.insert(productInWarehouse);
        });
    String method = warehousing.getMethod();
    if ("东岳头程".equals(method)) {
      warehousingMapper.statusUpdate(warehousingNo, "6", "7", UserUtils.getUserName(), new Date());
    } else {
      warehousingMapper.statusUpdate(warehousingNo, "4", "5", UserUtils.getUserName(), new Date());
    }
    return Json.succ();
  }

  @PostMapping("/addShelfContent")
  public Json addShelfContent(@RequestBody ShelfContent shelfContent) {
    List<UpShelfProduct> upShelfProductList = shelfContent.getContent();
    upShelfProductList.forEach(
        upshelfProduct -> {
          upshelfProduct.setUuid(SnowflakeIdWorker.generateStrId());
          upshelfProduct.setOwner(shelfContent.getOwner());
          upShelfProductMapper.insert(upshelfProduct);
          ProductInWarehouse productInWarehouse = new ProductInWarehouse();
          productInWarehouse.setUuid(SnowflakeIdWorker.generateStrId());
          productInWarehouse.setDySku(upshelfProduct.getDySku());
          productInWarehouse.setNum(upshelfProduct.getNum());
          productInWarehouse.setOwner(shelfContent.getOwner());
          productInWarehouse.setWarehousingNo(upshelfProduct.getWarehousingNo());
          productInWarehouse.setShelfNo(upshelfProduct.getShelfNo());
          productInWarehouse.setInTime(new Date());
          productInWarehouseRecordMapper.insert(productInWarehouse);
        });
    return Json.succ();
  }

  @PostMapping("/addShelfContentByFile")
  public Json addShelfContentByFile(
      @RequestParam(value = "file") MultipartFile multipartFile, @RequestParam String owner)
      throws IOException {
    StringBuilder msg = new StringBuilder();
    String uuid = UUID.randomUUID().toString().replace("-", "");
    File file = new File(uuid, multipartFile.getBytes());
    fileMapper.insert(file);
    String username = UserUtils.getUserName();
    if (StringUtils.isNotEmpty(owner)) {
      username = owner;
    }
    UserFileRecord userFileRecord =
        new UserFileRecord(multipartFile.getOriginalFilename(), new Date());
    userFileRecord.setUid(username);
    userFileRecord.setFileUuid(uuid);
    userFileRecordMapper.insert(userFileRecord);
    InputStream inputStream = multipartFile.getInputStream();
    List<Object> warehouseRecordList =
        EasyExcelFactory.read(inputStream, new Sheet(1, 0, UpShelfProductInFile.class));
    for (int i = 1; i < warehouseRecordList.size(); i++) {
      UpShelfProductInFile upShelfProductInFile = (UpShelfProductInFile) warehouseRecordList.get(i);
      try {
        UpShelfProduct upShelfProduct = new UpShelfProduct();
        upShelfProduct.setUuid(SnowflakeIdWorker.generateStrId());
        upShelfProduct.setOwner(owner);
        upShelfProduct.setDySku(upShelfProductInFile.getDySku());
        Product product =
            productMapper.selectProductByOwnerAndDySku(owner, upShelfProductInFile.getDySku());
        if (product == null) {
          throw new IllegalArgumentException(
              "东岳sku" + upShelfProductInFile.getDySku() + "不存在，导入失败;");
        }
        upShelfProduct.setNum(upShelfProductInFile.getNum());
        upShelfProduct.setShelfNo(upShelfProductInFile.getShelfNo());
        String inTime = upShelfProductInFile.getInTime();
        Date inTimeDate = DateUtil.getDateFromStr2(inTime);
        upShelfProduct.setUptime(inTimeDate);
        upShelfProduct.setWarehousingNo(upShelfProductInFile.getWarehousingNo());
        upShelfProductMapper.insert(upShelfProduct);
        ProductInWarehouse productInWarehouse = new ProductInWarehouse();
        productInWarehouse.setUuid(SnowflakeIdWorker.generateStrId());
        productInWarehouse.setDySku(upShelfProduct.getDySku());
        productInWarehouse.setNum(upShelfProduct.getNum());
        productInWarehouse.setOwner(upShelfProduct.getOwner());
        productInWarehouse.setWarehousingNo(upShelfProduct.getWarehousingNo());
        productInWarehouse.setShelfNo(upShelfProduct.getShelfNo());
        productInWarehouse.setInTime(inTimeDate);
        productInWarehouseRecordMapper.insert(productInWarehouse);
      } catch (Exception e) {
        log.error("error data {}", upShelfProductInFile);
        log.error("error stack info", e);
        msg.append(e.getMessage());
      }
    }
    return Json.succ().msg(msg.toString());
  }
}
