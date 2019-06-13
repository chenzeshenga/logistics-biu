package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.CompanyProfileMapper;
import com.abc.chenzeshenga.logistics.model.CompanyProfile;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.vo.Json;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author chenzeshenga
 * @version 1.0
 * @date 20190614
 */
@RestController @RequestMapping("/profile") public class CompanyProfileController {

    @Resource private CompanyProfileMapper companyProfileMapper;

    @GetMapping("/init") public Json initProfile() {
        return Json.succ().data(companyProfileMapper.init(UserUtils.getUserName()));
    }

    @PostMapping("/update") public Json updateProfile(@RequestBody CompanyProfile companyProfile) {
        CompanyProfile oriCompanyProfile = companyProfileMapper.init(UserUtils.getUserName());
        if (oriCompanyProfile == null) {
            companyProfile.setUserId(UserUtils.getUserName());
            companyProfileMapper.insertSelective(companyProfile);
        } else {
            companyProfileMapper.updateByPrimaryKeySelective(companyProfile);
        }
        return Json.succ();
    }

}
