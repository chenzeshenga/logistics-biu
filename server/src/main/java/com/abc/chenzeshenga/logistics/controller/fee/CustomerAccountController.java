package com.abc.chenzeshenga.logistics.controller.fee;

import com.abc.chenzeshenga.logistics.model.fee.CustomerAccountReq;
import com.abc.chenzeshenga.logistics.service.fee.CustomerAccountService;
import com.abc.vo.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenzeshenga
 */
@RestController
@RequestMapping("/finance/account")
public class CustomerAccountController {

    private CustomerAccountService customerAccountService;

    @Autowired
    public CustomerAccountController(CustomerAccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }

    @PostMapping("/list")
    public Json list(@RequestBody CustomerAccountReq customerAccountReq) {
        return Json.succ().data(customerAccountService.selectCustomerAccount(customerAccountReq));
    }

}
