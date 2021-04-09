package com.abc.chenzeshenga.logistics.service.fee;

import com.abc.chenzeshenga.logistics.mapper.fee.CustomerAccountMapper;
import com.abc.chenzeshenga.logistics.mapper.fee.CustomerFeeMapper;
import com.abc.chenzeshenga.logistics.mapper.fee.RechargeInfoMapper;
import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.abc.chenzeshenga.logistics.model.fee.CustomerAccountInfo;
import com.abc.chenzeshenga.logistics.model.fee.CustomerFee;
import com.abc.chenzeshenga.logistics.model.fee.RechargeInfo;
import com.abc.chenzeshenga.logistics.util.SnowflakeIdWorker;
import com.abc.dao.SysUserMapper;
import com.abc.entity.SysUser;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author chenzeshenga
 */
@Service
@Slf4j
public class CustomerAccountService extends ServiceImpl<CustomerAccountMapper, CustomerAccountInfo> {

    private final ExecutorService executorService;

    private final SysUserMapper sysUserMapper;

    private final RechargeInfoMapper rechargeInfoMapper;

    private final CustomerFeeMapper customerFeeMapper;

    @Autowired
    public CustomerAccountService(ExecutorService executorService, SysUserMapper sysUserMapper, RechargeInfoMapper rechargeInfoMapper, CustomerFeeMapper customerFeeMapper) {
        this.executorService = executorService;
        this.sysUserMapper = sysUserMapper;
        this.rechargeInfoMapper = rechargeInfoMapper;
        this.customerFeeMapper = customerFeeMapper;
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


}
