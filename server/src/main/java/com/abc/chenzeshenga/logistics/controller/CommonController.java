package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.ImgMapper;
import com.abc.chenzeshenga.logistics.model.Img;
import com.abc.chenzeshenga.logistics.util.CommonUtil;
import com.abc.chenzeshenga.logistics.util.SkuUtil;
import com.abc.vo.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author chenzesheng
 * @version 1.0
 */
@RestController @Slf4j public class CommonController {

    @Resource private ImgMapper imgMapper;

    @GetMapping("/generate/pk") public Json getOrderNo() {
        String pk = CommonUtil.generate();
        return Json.succ().data(pk);
    }

    @GetMapping("/generate/sku") public Json getDySku() {
        String sku = SkuUtil.generateDySku();
        return Json.succ().data(sku);
    }

    @RequestMapping(value = "/img/{uuid}", produces = MediaType.IMAGE_JPEG_VALUE) @ResponseBody public byte[] getImg(@PathVariable String uuid) {
        Img img = imgMapper.selectByPrimaryKey(uuid);
        byte[] bytes = img.getImg();
        return bytes;
    }

    @PostMapping @RequestMapping(value = "/img") public Json putImg(@RequestParam(value = "file") MultipartFile multipartFile, String uuid)
        throws IOException {
        Img img = new Img(uuid, multipartFile.getBytes());
        imgMapper.insert(img);
        return Json.succ();
    }

    @GetMapping("/ord/excel/{ordno}") public void getOrdExcel(@PathVariable String ordno) {

    }

    @GetMapping("/ord/csv/{ordno}") public void getOrdCsv(@PathVariable String ordno) {

    }

    @GetMapping("/warehousing/excel/{ordno}") public void getWarehousingExcel(@PathVariable String ordno) {

    }

    @GetMapping("/warehousing/csv/{ordno}") public void getWarehousingCsv(@PathVariable String ordno) {

    }

}
