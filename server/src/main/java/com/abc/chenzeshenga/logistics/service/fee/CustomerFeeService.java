package com.abc.chenzeshenga.logistics.service.fee;

import com.abc.chenzeshenga.logistics.mapper.fee.CustomerFeeMapper;
import com.abc.chenzeshenga.logistics.mapper.file.FileRecordMapper;
import com.abc.chenzeshenga.logistics.model.common.PageData;
import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.abc.chenzeshenga.logistics.model.fee.CustomerFee;
import com.abc.chenzeshenga.logistics.model.fee.CustomerFeeHead;
import com.abc.chenzeshenga.logistics.model.fee.CustomerFeeReq;
import com.abc.chenzeshenga.logistics.model.file.FileRecord;
import com.abc.chenzeshenga.logistics.model.ord.ManualOrderV2;
import com.abc.chenzeshenga.logistics.model.user.CustSysUser;
import com.abc.chenzeshenga.logistics.model.user.CustSysUserHead;
import com.abc.chenzeshenga.logistics.service.common.FileService;
import com.abc.chenzeshenga.logistics.service.user.UserService;
import com.abc.chenzeshenga.logistics.util.DateUtil;
import com.abc.chenzeshenga.logistics.util.FileUtils;
import com.abc.chenzeshenga.logistics.util.SnowflakeIdWorker;
import com.abc.chenzeshenga.logistics.util.SqlUtils;
import com.abc.controller.SysUserController;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenzeshenga
 */
@Service
public class CustomerFeeService extends ServiceImpl<CustomerFeeMapper, CustomerFee> {

    private static final String tempPath = "/app/temp";

    private final FileRecordMapper fileRecordMapper;

    private final UserService userService;

    private final FileService fileService;

    @Autowired
    public CustomerFeeService(FileRecordMapper fileRecordMapper, UserService userService, FileService fileService) {
        this.fileRecordMapper = fileRecordMapper;
        this.userService = userService;
        this.fileService = fileService;
    }

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

    @Override
    public CustomerFee selectById(Serializable id) {
        return baseMapper.selectById(id);
    }

    public FileRecord generateFile(CustomerFeeReq customerFeeReq) throws FileNotFoundException {
        List<CustomerFee> customerFeeList = baseMapper.selectCustomerFeeListWithoutPagination(customerFeeReq.getStartMonth(), customerFeeReq.getEndMonth(), customerFeeReq.getUserId());
        String uuid = SnowflakeIdWorker.generateStrId();
        String currMonth = DateUtil.getMonthFromDate(new Date());
        FileUtils.createDirectory(tempPath + File.separator + currMonth);
        File tmpFile = new File(tempPath + File.separator + currMonth + File.separator + uuid + ".xlsx");
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile);
        ExcelWriter excelWriter = new ExcelWriter(fileOutputStream, ExcelTypeEnum.XLSX);
        Sheet sheet1 = new Sheet(1, 0, CustomerFee.class);
        excelWriter.write(customerFeeList, sheet1);
        excelWriter.finish();

        FileRecord fileRecord = new FileRecord();
        fileRecord.setUuid(uuid);
        fileRecord.setFileName(uuid + "-客户账单列表-" + DateUtil.getOnlyDateStrFromDate(new Date()) + ".xlsx");
        fileRecord.setAbsolutePath(tempPath + File.separator + currMonth + File.separator + uuid + ".xlsx");
        fileRecord.setUploadUser("sys");
        fileRecord.setUploadDate(new Date());
        fileRecordMapper.insert(fileRecord);
        return fileRecord;
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
        Sheet sheet1 = new Sheet(1, 0, CustomerFeeHead.class);
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

    public void batchImport(MultipartFile multipartFile) throws IOException, InvocationTargetException, IllegalAccessException {
        fileService.upload(multipartFile);
        List<Object> customerFeeObjList = EasyExcelFactory.read(multipartFile.getInputStream(), new Sheet(1, 1, CustomerFeeHead.class));
        for (Object customerFeeObj : customerFeeObjList) {
            if (customerFeeObj instanceof CustomerFeeHead) {
                CustomerFeeHead customerFeeHead = (CustomerFeeHead) customerFeeObj;
                CustomerFee customerFee = new CustomerFee();
                BeanUtils.copyProperties(customerFee, customerFeeHead);
                customerFee.setUuid(SnowflakeIdWorker.generateStrId());
                insert(customerFee);
            }
        }
    }

    @Override
    public boolean insert(CustomerFee entity) {
        return retBool(baseMapper.insert(entity));
    }
}
