package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.CompanyProfileMapper;
import com.abc.chenzeshenga.logistics.model.CompanyProfile;
import com.abc.chenzeshenga.logistics.model.Product;
import com.abc.chenzeshenga.logistics.model.common.PageQueryEntity;
import com.abc.chenzeshenga.logistics.service.company.CompanyProfileService;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.vo.Json;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 * @date 20190614
 */
@RestController
@RequestMapping("/profile")
public class CompanyProfileController {

  @Resource private CompanyProfileMapper companyProfileMapper;

  private CompanyProfileService companyProfileService;

  @Autowired
  public CompanyProfileController(CompanyProfileService companyProfileService) {
    this.companyProfileService = companyProfileService;
  }

  @GetMapping("/init")
  public Json initProfile(@RequestParam(required = false) String userId) {
    CompanyProfile companyProfile;
    if (StringUtils.isBlank(userId)) {
      companyProfile = companyProfileService.init(UserUtils.getUserName());
    } else {
      companyProfile = companyProfileService.init(userId);
    }
    return Json.succ().data(companyProfile);
  }

  @PostMapping("/list")
  public Json listProfile(@RequestBody PageQueryEntity<String> userIdPageQueryEntity) {
    return Json.succ().data(companyProfileService.listProfile(userIdPageQueryEntity));
  }

  @PostMapping("/update")
  public Json updateProfile(
      @RequestBody CompanyProfile companyProfile, @RequestParam(required = false) String userId) {
    if (StringUtils.isBlank(userId)) {
      userId = UserUtils.getUserName();
    }
    CompanyProfile oriCompanyProfile = companyProfileMapper.init(userId);
    if (oriCompanyProfile == null) {
      companyProfile.setUserId(userId);
      companyProfileMapper.insertSelective(companyProfile);
    } else {
      companyProfileMapper.updateByPrimaryKeySelective(companyProfile);
    }
    return Json.succ();
  }

  @PostMapping("/mgtUpdate")
  public Json mgtUpdateProfile(@RequestBody CompanyProfile companyProfile) {
    String userId = companyProfile.getUserId();
    CompanyProfile oriCompanyProfile = companyProfileMapper.init(userId);
    if (oriCompanyProfile == null) {
      companyProfile.setUserId(userId);
      companyProfileMapper.insertSelective(companyProfile);
    } else {
      companyProfileMapper.updateCostOnVolume(companyProfile);
    }
    return Json.succ();
  }
}
