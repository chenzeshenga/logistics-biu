package com.abc.chenzeshenga.logistics.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chenzesheng
 * @version 1.0
 * @since 2019.7.19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("l_returning")
public class Return implements Serializable {

  private static final long serialVersionUID = -6249013466703476021L;

  @TableId(type = IdType.ID_WORKER_STR)
  private String returnNo;

  private boolean withoutOrderNoFlag;
  private String toName;
  private String toContact;
  private String toZipCode;
  private String toDetailAddress;
  private String toKenId;
  private String toCityId;
  private String toTownId;
  private String fromName;
  private String fromContact;
  private String fromZipCode;
  private String fromDetailAddress;
  private String fromKenId;
  private String fromCityId;
  private String fromTownId;
  private String carrier;
  private String trackNo;
  private String imgs;
  private String status;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createOn;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateOn;

  @TableField(value = "create_by")
  private String creator;

  @TableField(value = "update_by")
  private String updator;

  private String orderNo;

  @TableField(exist = false)
  private List<ReturnContent> contentList;
}
