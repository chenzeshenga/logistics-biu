package com.abc.chenzeshenga.logistics.service.company;

import com.abc.chenzeshenga.logistics.mapper.CompanyProfileMapper;
import com.abc.chenzeshenga.logistics.model.CompanyProfile;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chenzeshenga
 * @since 2020-12-21
 */
@Service
public class CompanyProfileService {

  @Resource private CompanyProfileMapper companyProfileMapper;

  public CompanyProfile init(String userId) {
    return companyProfileMapper.init(userId);
  }
}
