package com.abc.chenzeshenga.logistics.controller.fee;

import com.abc.chenzeshenga.logistics.model.fee.CustomerFee;
import com.abc.chenzeshenga.logistics.model.fee.CustomerFeeReq;
import com.abc.chenzeshenga.logistics.service.fee.CustomerFeeService;
import com.abc.chenzeshenga.logistics.util.SnowflakeIdWorker;
import com.abc.vo.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

/**
 * @author chenzeshenga
 */
@RestController
@RequestMapping("/finance/fee")
public class CustomerFeeController {

    private final CustomerFeeService customerFeeService;

    @Autowired
    public CustomerFeeController(CustomerFeeService customerFeeService) {
        this.customerFeeService = customerFeeService;
    }

    @PostMapping("/add")
    public Json add(@RequestBody CustomerFee customerFee) {
        String uuid = SnowflakeIdWorker.generateStrId();
        customerFee.setUuid(uuid);
        customerFeeService.insert(customerFee);
        return Json.succ().data(customerFeeService.selectById(uuid));
    }

    @PostMapping("/list")
    public Json list(@RequestBody CustomerFeeReq customerFeeReq) {
        return Json.succ().data(customerFeeService.selectCustomerFee(customerFeeReq));
    }

    @GetMapping("/delete/{uuid}")
    public Json delete(@PathVariable String uuid) {
        return Json.succ().data(customerFeeService.deleteById(uuid));
    }

    @GetMapping("/detail/{uuid}")
    public Json detail(@PathVariable String uuid) {
        return Json.succ().data(customerFeeService.selectById(uuid));
    }

    @PostMapping("/generateFile")
    public Json generateFile(@RequestBody CustomerFeeReq customerFeeReq) throws FileNotFoundException {
        return Json.succ().data("data", customerFeeService.generateFile(customerFeeReq));
    }

    @GetMapping("/generateTemplate")
    public Json generateTemplate() throws FileNotFoundException {
        return Json.succ().data("data", customerFeeService.generateTemplate());
    }

}
