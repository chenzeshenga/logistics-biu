package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.CompanyProfile;
import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 * @date 20190614
 */
@Mapper
public interface CompanyProfileMapper {

  /**
   * 根据userId获取企业信息
   *
   * @param userId 用户id
   * @return 企业信息
   */
  CompanyProfile init(String userId);

  /**
   * 根据用户id分页查询企业信息
   *
   * @param userId 用户id
   * @param sqlLimit 分页信息
   * @return 符合条件的数组
   */
  List<CompanyProfile> list(@Param("userId") String userId, @Param("limit") SqlLimit sqlLimit);

  long count(@Param("userId") String userId);

  int insertSelective(CompanyProfile companyProfile);

  int updateByPrimaryKeySelective(CompanyProfile companyProfile);

  int updateCostOnVolume(CompanyProfile companyProfile);
}
