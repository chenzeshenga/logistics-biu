package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.CompanyProfileMapper;
import com.abc.chenzeshenga.logistics.model.CompanyProfile;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.vo.Json;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author chenzeshenga
 * @version 1.0
 * @date 20190614
 */
@RestController @RequestMapping("/profile") public class CompanyProfileController {

    @Resource private CompanyProfileMapper companyProfileMapper;

    @GetMapping("/init") public Json initProfile(@RequestParam(required = false) String userId) {
        CompanyProfile companyProfile;
        if (StringUtils.isBlank(userId)) {
            companyProfile = companyProfileMapper.init(UserUtils.getUserName());
        } else {
            companyProfile = companyProfileMapper.init(userId);
        }
        return Json.succ().data(companyProfile);
    }

    @PostMapping("/update") public Json updateProfile(@RequestBody CompanyProfile companyProfile,
        @RequestParam(required = false) String userId) {
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

}