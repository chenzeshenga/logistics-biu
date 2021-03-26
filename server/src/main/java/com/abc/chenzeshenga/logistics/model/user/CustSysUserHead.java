package com.abc.chenzeshenga.logistics.model.user;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author chenzeshenga
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustSysUserHead extends BaseRowModel {

    @ExcelProperty(index = 1, value = "用户id")
    private String nick;

    @ExcelProperty(index = 2, value = "用户名")
    private String uname;

}
