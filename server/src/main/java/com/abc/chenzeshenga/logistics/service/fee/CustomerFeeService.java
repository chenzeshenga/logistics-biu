package com.abc.chenzeshenga.logistics.service.fee;

import com.abc.chenzeshenga.logistics.mapper.fee.CustomerFeeMapper;
import com.abc.chenzeshenga.logistics.model.common.PageData;
import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.abc.chenzeshenga.logistics.model.fee.CustomerFee;
import com.abc.chenzeshenga.logistics.model.fee.CustomerFeeReq;
import com.abc.chenzeshenga.logistics.util.SqlUtils;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenzeshenga
 */
@Service
public class CustomerFeeService extends ServiceImpl<CustomerFeeMapper, CustomerFee> {

    public PageData<CustomerFee> selectCustomerFee(CustomerFeeReq customerFeeReq) {
        SqlLimit sqlLimit = SqlUtils.generateSqlLimit(customerFeeReq);
        List<CustomerFee> customerFeeList = baseMapper.selectCustomerFeeList(sqlLimit, customerFeeReq.getStartMonth(), customerFeeReq.getEndMonth(), customerFeeReq.getUserId());
        long total = baseMapper.countCustomerFeeList(customerFeeReq.getStartMonth(), customerFeeReq.getEndMonth(), customerFeeReq.getUserId());
        customerFeeReq.setTotal(total);
        PageData<CustomerFee> customerFeePageData = new PageData<>();
        customerFeePageData.setData(customerFeeList);
        customerFeePageData.setPagination(customerFeeReq);
        return customerFeePageData;
    }

}
