package com.abc.chenzeshenga.logistics.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Data @EqualsAndHashCode(callSuper = false) public class ManualOrder4Input extends BaseRowModel
    implements Serializable {

    @ExcelProperty(index = 0, value = "订单类型") private String categoryName;
    @ExcelProperty(index = 1, value = "发件地址") private String fromKenName;
    @ExcelProperty(index = 2, value = "发件地址邮编") private String fromZipCode;
    @ExcelProperty(index = 3, value = "发件人姓名") private String fromName;
    @ExcelProperty(index = 4, value = "发件人联系方式") private String fromContact;
    @ExcelProperty(index = 5, value = "收件地址") private String toDetailAddress;
    @ExcelProperty(index = 6, value = "收件地址邮编") private String toZipCode;
    @ExcelProperty(index = 7, value = "收件人姓名") private String toName;
    @ExcelProperty(index = 8, value = "收件人联系方式") private String toContact;
    @ExcelProperty(index = 9, value = "是否代收费用") private String collectDesc;
    @ExcelProperty(index = 10, value = "代收费用总额(JPY)") private String collectNum;
    @ExcelProperty(index = 11, value = "sku") private String sku1;
    @ExcelProperty(index = 12, value = "名称") private String name1;
    @ExcelProperty(index = 13, value = "数量") private String num1;
    @ExcelProperty(index = 14, value = "sku") private String sku2;
    @ExcelProperty(index = 15, value = "名称") private String name2;
    @ExcelProperty(index = 16, value = "数量") private String num2;
    @ExcelProperty(index = 17, value = "sku") private String sku3;
    @ExcelProperty(index = 18, value = "名称") private String name3;
    @ExcelProperty(index = 19, value = "数量") private String num3;

}
