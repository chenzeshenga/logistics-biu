package com.abc.chenzeshenga.logistics.temp.controller;

import com.abc.chenzeshenga.logistics.model.ManualOrder4Input;
import com.abc.chenzeshenga.logistics.temp.entity.TmpOrder;
import com.abc.chenzeshenga.logistics.temp.service.TmpService;
import com.abc.vo.Json;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author chenzeshenga
 * @since 2020-05-07 22:49
 */
@RestController
@Slf4j
public class TempController {

  private TmpService tmpService;

  @Autowired
  public TempController(TmpService tmpService) {
    this.tmpService = tmpService;
  }

  @PostMapping("/temp/order/update")
  public Json updateOrder(@RequestParam(value = "file") MultipartFile multipartFile)
      throws IOException {
    InputStream inputStream = multipartFile.getInputStream();
    List<Object> tmpOrderList = EasyExcelFactory.read(inputStream, new Sheet(1, 0, TmpOrder.class));
    tmpOrderList.forEach(
        tmpOrder -> {
          if (tmpOrder instanceof TmpOrder) {
            TmpOrder order = (TmpOrder) tmpOrder;
            tmpService.updateOrder(order);
          }
        });
    return Json.succ();
  }
}
