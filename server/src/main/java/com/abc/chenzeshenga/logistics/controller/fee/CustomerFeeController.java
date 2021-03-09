package com.abc.chenzeshenga.logistics.controller.fee;

import com.abc.chenzeshenga.logistics.model.fee.CustomerFee;
import com.abc.chenzeshenga.logistics.service.fee.CustomerFeeService;
import com.abc.chenzeshenga.logistics.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenzeshenga
 */
@RestController
@RequestMapping("/finance/fee")
public class CustomerFeeController {

    private CustomerFeeService customerFeeService;

    @Autowired
    public CustomerFeeController(CustomerFeeService customerFeeService) {
        this.customerFeeService = customerFeeService;
    }

    @PostMapping("/add")
    public void add(@RequestBody CustomerFee customerFee) {
        customerFee.setUuid(SnowflakeIdWorker.generateStrId());
        customerFeeService.insert(customerFee);
    }

}
