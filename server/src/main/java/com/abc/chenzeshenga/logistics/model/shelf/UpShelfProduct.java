package com.abc.chenzeshenga.logistics.model.shelf;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenzeshenga
 * @since 2020-01-01 23:33
 */
@Data
@TableName(value = "l_up_shelf_product")
@NoArgsConstructor
@AllArgsConstructor
public class UpShelfProduct {

  @TableId(type = IdType.ID_WORKER_STR)
  private String uuid;

  private String dySku;
  private String name;
  private String num;
  private String shelfNo;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date uptime;

  private String owner;
  private String warehousingNo;

  @TableField(exist = false)
  private String datePoor;

  public UpShelfProduct(String dySku, String owner, String shelfNo) {
    this.dySku = dySku;
    this.owner = owner;
    this.shelfNo = shelfNo;
  }
}
