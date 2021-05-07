package com.abc.chenzeshenga.logistics.controller.fee;

import com.abc.chenzeshenga.logistics.model.fee.CustomerFeeReq;
import com.abc.chenzeshenga.logistics.model.fee.RechargeInfo;
import com.abc.chenzeshenga.logistics.model.fee.RechargeInfoReq;
import com.abc.chenzeshenga.logistics.service.fee.RechargeInfoService;
import com.abc.vo.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author chenzeshenga
 */
@RestController
@RequestMapping("/finance/recharge")
public class RechargeInfoController {

    private final RechargeInfoService rechargeInfoService;

    @Autowired
    public RechargeInfoController(RechargeInfoService rechargeInfoService) {
        this.rechargeInfoService = rechargeInfoService;
    }

    @PostMapping("/add")
    public Json add(@RequestBody RechargeInfo rechargeInfo) {
        rechargeInfoService.insert(rechargeInfo);
        return Json.succ();
    }

    @PostMapping("/batch/import")
    public Json batchImport(@RequestBody MultipartFile file) throws IOException {
        rechargeInfoService.batchImport(file);
        return Json.succ();
    }

    @PostMapping("/list")
    public Json list(@RequestBody RechargeInfoReq rechargeInfoReq) {
        return Json.succ().data(rechargeInfoService.selectRechargeInfo(rechargeInfoReq));
    }


    @GetMapping("/generateRechargeFile")
    public Json generateRechargeFile(@RequestParam("userId") String userId) throws FileNotFoundException {
        return Json.succ().data("data", rechargeInfoService.generateRechargeFile(userId));
    }

}
