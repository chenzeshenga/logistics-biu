package com.abc.chenzeshenga.logistics.service.fee;

import com.abc.chenzeshenga.logistics.mapper.fee.CustomerAccountMapper;
import com.abc.chenzeshenga.logistics.mapper.fee.CustomerFeeMapper;
import com.abc.chenzeshenga.logistics.mapper.fee.RechargeInfoMapper;
import com.abc.chenzeshenga.logistics.mapper.file.FileRecordMapper;
import com.abc.chenzeshenga.logistics.model.common.PageData;
import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.abc.chenzeshenga.logistics.model.fee.CustomerAccountInfo;
import com.abc.chenzeshenga.logistics.model.fee.CustomerAccountReq;
import com.abc.chenzeshenga.logistics.model.fee.CustomerFee;
import com.abc.chenzeshenga.logistics.model.fee.CustomerFeeHead;
import com.abc.chenzeshenga.logistics.model.fee.RechargeInfo;
import com.abc.chenzeshenga.logistics.model.fee.RechargeInfoHead;
import com.abc.chenzeshenga.logistics.model.file.FileRecord;
import com.abc.chenzeshenga.logistics.model.user.CustSysUser;
import com.abc.chenzeshenga.logistics.model.user.CustSysUserHead;
import com.abc.chenzeshenga.logistics.service.user.UserService;
import com.abc.chenzeshenga.logistics.util.DateUtil;
import com.abc.chenzeshenga.logistics.util.FileUtils;
import com.abc.chenzeshenga.logistics.util.SnowflakeIdWorker;
import com.abc.chenzeshenga.logistics.util.SqlUtils;
import com.abc.dao.SysUserMapper;
import com.abc.entity.SysUser;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author chenzeshenga
 */
@Service
@Slf4j
public class CustomerAccountService extends ServiceImpl<CustomerAccountMapper, CustomerAccountInfo> {

    private static final String tempPath = "/app/temp";

    private final ExecutorService executorService;

    private final SysUserMapper sysUserMapper;

    private final RechargeInfoMapper rechargeInfoMapper;

    private final CustomerFeeMapper customerFeeMapper;

    private final UserService userService;

    private final FileRecordMapper fileRecordMapper;

    @Autowired
    public CustomerAccountService(ExecutorService executorService, SysUserMapper sysUserMapper, RechargeInfoMapper rechargeInfoMapper, CustomerFeeMapper customerFeeMapper, UserService userService, FileRecordMapper fileRecordMapper) {
        this.executorService = executorService;
        this.sysUserMapper = sysUserMapper;
        this.rechargeInfoMapper = rechargeInfoMapper;
        this.customerFeeMapper = customerFeeMapper;
        this.userService = userService;
        this.fileRecordMapper = fileRecordMapper;
    }

    @Scheduled(fixedRate = 7200000)
    @PostConstruct
    public void triggerAccount() {
        executorService.execute(() -> {
            log.info("CustomerAccountService.triggerAccount start");
            baseMapper.truncate();
            //获取所有用户
            List<SysUser> sysUserList = sysUserMapper.selectAll();
            for (SysUser sysUser : sysUserList) {
                CustomerAccountInfo customerAccountInfo = new CustomerAccountInfo();
                String userId = sysUser.getUname();
                customerAccountInfo.setUuid(SnowflakeIdWorker.generateStrId());
                customerAccountInfo.setUserId(userId);
                //统计充值记录
                List<RechargeInfo> rechargeInfoList = rechargeInfoMapper.selectRechargeInfo(new SqlLimit(0, 1000), userId);
                BigDecimal amountInJpy = new BigDecimal("0");
                BigDecimal amountInCny = new BigDecimal("0");
                for (RechargeInfo rechargeInfo : rechargeInfoList) {
                    amountInCny = amountInCny.add(rechargeInfo.getAmountInCny());
                    amountInJpy = amountInJpy.add(rechargeInfo.getAmountInJpy());
                }
                customerAccountInfo.setTotalInJpy(amountInJpy);
                customerAccountInfo.setTotalInCny(amountInCny);
                //统计消费记录
                List<CustomerFee> customerFeeList = customerFeeMapper.selectCustomerFeeList(new SqlLimit(0, 1000), null, null, userId);
                BigDecimal feeInJpy = new BigDecimal("0");
                BigDecimal feeInCny = new BigDecimal("0");
                for (CustomerFee customerFee : customerFeeList) {
                    String currency = customerFee.getCurrency();
                    if ("JPY".equalsIgnoreCase(currency)) {
                        feeInJpy = feeInJpy.add(customerFee.getFee1()).add(customerFee.getFee2()).add(customerFee.getFee3())
                            .add(customerFee.getFee4()).add(customerFee.getFee5()).add(customerFee.getFee6()).add(customerFee.getFee7())
                            .add(customerFee.getFee8()).add(customerFee.getFee9());
                    } else if ("CNY".equalsIgnoreCase(currency)) {
                        feeInCny = feeInCny.add(customerFee.getFee1()).add(customerFee.getFee2()).add(customerFee.getFee3())
                            .add(customerFee.getFee4()).add(customerFee.getFee5()).add(customerFee.getFee6()).add(customerFee.getFee7())
                            .add(customerFee.getFee8()).add(customerFee.getFee9());
                    }
                }
                customerAccountInfo.setFeeInJpy(feeInJpy);
                customerAccountInfo.setFeeInCny(feeInCny);
                customerAccountInfo.setTimestamp(new Date());
                baseMapper.insert(customerAccountInfo);
            }
            log.info("CustomerAccountService.triggerAccount end");
        });
    }

    public PageData<CustomerAccountInfo> selectCustomerAccount(CustomerAccountReq customerAccountReq) {
        SqlLimit sqlLimit = SqlUtils.generateSqlLimit(customerAccountReq);
        List<CustomerAccountInfo> customerAccountInfoList = baseMapper.selectCustomerAccountList(sqlLimit, customerAccountReq.getUserId());
        long total = baseMapper.countCustomerAccountList(customerAccountReq.getUserId());
        customerAccountReq.setTotal(total);
        PageData<CustomerAccountInfo> customerAccountInfoPageData = new PageData<>();
        customerAccountInfoPageData.setData(customerAccountInfoList);
        customerAccountInfoPageData.setPagination(customerAccountReq);
        return customerAccountInfoPageData;
    }


    public FileRecord generateTemplate() throws FileNotFoundException {
        String uuid = SnowflakeIdWorker.generateStrId();
        String currMonth = DateUtil.getMonthFromDate(new Date());
        FileUtils.createDirectory(tempPath + File.separator + currMonth);
        File tmpFile = new File(tempPath + File.separator + currMonth + File.separator + uuid + ".xlsx");
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile);
        Page<CustSysUser> custSysUserPage = userService.selectPage(new Page<>(0, 500));
        List<CustSysUser> custSysUserList = custSysUserPage.getRecords();
        List<CustSysUserHead> custSysUserHeadList = new ArrayList<>();
        custSysUserList.forEach(custSysUser -> {
            CustSysUserHead customerFeeHead = new CustSysUserHead(custSysUser.getUname(), custSysUser.getNick());
            custSysUserHeadList.add(customerFeeHead);
        });
        ExcelWriter excelWriter = new ExcelWriter(fileOutputStream, ExcelTypeEnum.XLSX);
        Sheet sheet1 = new Sheet(1, 0, RechargeInfoHead.class);
        Sheet sheet2 = new Sheet(2, 0, CustSysUserHead.class);
        excelWriter.write(null, sheet1);
        excelWriter.write(custSysUserHeadList, sheet2);
        excelWriter.finish();
        FileRecord fileRecord = new FileRecord();
        fileRecord.setUuid(uuid);
        fileRecord.setFileName(uuid + ".xlsx");
        fileRecord.setAbsolutePath(tempPath + File.separator + currMonth + File.separator + uuid + ".xlsx");
        fileRecord.setUploadUser("sys");
        fileRecord.setUploadDate(new Date());
        fileRecordMapper.insert(fileRecord);
        return fileRecord;
    }

}
