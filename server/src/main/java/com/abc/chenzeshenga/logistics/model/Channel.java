package com.abc.chenzeshenga.logistics.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * l_channel
 *
 * @author
 */
@Data
public class Channel implements Serializable {

  private static final long serialVersionUID = 3847302764352457071L;

  private String channelCode;

  private String channelName;

  private String partner;

  private String partnerDesc;

  private String min;

  private String max;

  private String checkedRules;

  private String ruleDesc;

  private List<String> checkedRules2;

  private String active;

  private String adapter;

  private String adapterDesc;

  private String rule;

  private String calculateRuleDesc;

  private String comments;

  private String createBy;

  private String updateBy;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createOn;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateOn;
}
