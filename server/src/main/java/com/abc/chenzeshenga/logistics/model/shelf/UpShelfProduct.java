package com.abc.chenzeshenga.logistics.model.shelf;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;

/**
 * @author chenzeshenga
 * @since 2020-01-01 23:33
 */
@Data
@TableName(value = "l_up_shelf_product")
public class UpShelfProduct {

  private String uuid;
  private String sku;
  private String name;
  private String num;
  private String shelfNo;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date uptime;

  private String owner;
  private String warehousingNo;

  @TableField(exist = false)
  private String datePoor;
}
