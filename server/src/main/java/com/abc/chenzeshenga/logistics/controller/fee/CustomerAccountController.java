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
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author chenzeshenga
 */
@RestController
@RequestMapping("/finance/account")
public class CustomerAccountController {


}
