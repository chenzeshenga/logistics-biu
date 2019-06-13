package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.CompanyProfile;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenzeshenga
 * @version 1.0
 * @date 20190614
 */
@Mapper public interface CompanyProfileMapper {

    CompanyProfile init(String userId);

    int insertSelective(CompanyProfile companyProfile);

    int updateByPrimaryKeySelective(CompanyProfile companyProfile);

}
