package com.abc.chenzeshenga.logistics.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @author chenzeshenga
 * @version 1.0
 * @date 20190614
 */
@Data
public class CompanyProfile {

  private String userId;
  private String chineseName;
  private String englishName;
  private String chineseAddr;
  private String englishAddr;
  private String zipCode;
  private String contactEnglishName;
  private String contactChineseName;
  private String phone;
  private BigDecimal costOnVolume;

  private Date deliverDate;
  private String trackNo;
  private String warehousingNo;
}
