package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.cache.LabelCache;
import com.abc.chenzeshenga.logistics.mapper.*;
import com.abc.chenzeshenga.logistics.model.*;
import com.abc.chenzeshenga.logistics.model.File;
import com.abc.chenzeshenga.logistics.service.TemplateService;
import com.abc.chenzeshenga.logistics.service.WarehousingService;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.chenzeshenga.logistics.util.print.CustomsDeclarationUtil;
import com.abc.entity.SysUser;
import com.abc.util.PageUtils;
import com.abc.vo.AuthVo;
import com.abc.vo.Json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author chenzesheng
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/warehousing")
public class WarehousingController {

    @Resource
    private WarehousingMapper warehousingMapper;

    @Resource
    private WarehousingContentMapper warehousingContentMapper;

    @Resource
    private CompanyProfileMapper companyProfileMapper;

    @Resource
    private FileMapper fileMapper;

    private WarehousingService warehousingService;

    private LabelCache labelCache;

    private CustomsDeclarationUtil customsDeclarationUtil;

    @Autowired
    public WarehousingController(WarehousingService warehousingService, LabelCache labelCache,
            CustomsDeclarationUtil customsDeclarationUtil) {
        this.warehousingService = warehousingService;
        this.labelCache = labelCache;
        this.customsDeclarationUtil = customsDeclarationUtil;
    }

    @PostMapping
    @RequestMapping("/add")
    public Json add(@RequestBody @Valid Warehousing warehousing, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errMsg = getErrMsg(bindingResult);
            return Json.fail().msg(errMsg.toString());
        }
        String username = UserUtils.getUserName();
        if (StringUtils.isEmpty(warehousing.getCreator())) {
            warehousing.setCreator(username);
        }
        warehousing.setUpdator(username);
        Date curr = new Date();
        warehousing.setCreateOn(curr);
        warehousing.setUpdateOn(curr);
        warehousing.setStatus("1");
        warehousing.setCarrier("东岳头程");
        warehousing.setTrackNo(warehousing.getWarehousingNo());
        List<WarehousingContent> warehousingContentList = warehousing.getWarehousingContentList();
        warehousingMapper.insert(warehousing);
        warehousingContentMapper.insertList(warehousingContentList);
        return Json.succ();
    }

    @PostMapping
    @RequestMapping("/update")
    public Json update(@RequestBody Warehousing warehousing) {
        String username = UserUtils.getUserName();
        warehousing.setUpdator(username);
        Date curr = new Date();
        warehousing.setUpdateOn(curr);
        List<WarehousingContent> warehousingContentList = warehousing.getWarehousingContentList();
        warehousingMapper.updateByPrimaryKeySelective(warehousing);
        warehousingContentMapper.deleteContent(warehousing.getWarehousingNo());
        warehousingContentMapper.insertList(warehousingContentList);
        return Json.succ();
    }

    @PostMapping
    @RequestMapping("/list/{method}/{status}")
    public Json list(@RequestBody String body, @PathVariable String method, @PathVariable String status) {
        method = switchMethod(method);
        String cname = UserUtils.getUserName();
        JSONObject jsonObject = JSON.parseObject(body);
        Page page = PageUtils.getPageParam(jsonObject);
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getPrincipal();
        Set<AuthVo> authVos = user.getRoles();
        AtomicBoolean queryAll = new AtomicBoolean(false);
        authVos.forEach(authVo -> {
            if ("root".equals(authVo.getVal()) || "operator".equals(authVo.getVal())) {
                queryAll.set(true);
            }
        });
        Page<Warehousing> warehousingPage;
        if (queryAll.get()) {
            warehousingPage = warehousingService.listByStatus(page, status, method);
        } else {
            warehousingPage = warehousingService.listByOwnerAndStatus(page, cname, method, status);
        }
        enrichWarehousing(warehousingPage);
        return Json.succ().data("page", warehousingPage);
    }

    @PostMapping("/listByFilter/{method}/{status}")
    public Json listByFilter(@RequestBody String body, @PathVariable String method, @PathVariable String status) {
        method = switchMethod(method);
        String cname = UserUtils.getUserName();
        JSONObject jsonObject = JSON.parseObject(body);
        Page page = PageUtils.getPageParam(jsonObject);
        WarehousingReq warehousingReq = new WarehousingReq();
        warehousingReq.setWarehousingNo(jsonObject.getString("warehousingNo"));
        warehousingReq.setCreator(jsonObject.getString("creator"));
        warehousingReq.setChannelCode(jsonObject.getString("channelCode"));
        warehousingReq.setFrom(jsonObject.getDate("from"));
        warehousingReq.setTo(jsonObject.getDate("to"));
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getPrincipal();
        Set<AuthVo> authVos = user.getRoles();
        AtomicBoolean queryAll = new AtomicBoolean(false);
        authVos.forEach(authVo -> {
            if ("root".equals(authVo.getVal()) || "operator".equals(authVo.getVal())) {
                queryAll.set(true);
            }
        });
        Page<Warehousing> warehousingPage;
        if (queryAll.get()) {
            warehousingPage = warehousingService.listByStatusAndFilter(page, status, method, warehousingReq);
        } else {
            warehousingPage = warehousingService.listByOwnerAndStatusAndFilter(page, cname, method, status,
                    warehousingReq);
        }
        enrichWarehousing(warehousingPage);
        return Json.succ().data("page", warehousingPage);
    }

    private String switchMethod(String method) {
        switch (method) {
        case "2":
            return "其他头程";
        case "1":
        default:
            return "东岳头程";
        }
    }

    private void enrichWarehousing(Page<Warehousing> warehousingPage) {
        List<Warehousing> warehousingList = warehousingPage.getRecords();
        warehousingList.forEach(warehousing -> {
            warehousing.setStatusDesc(labelCache.getLabel("warehousing_" + warehousing.getStatus()));
        });
    }

    @PostMapping
    @RequestMapping("/status")
    public Json statusUpdate(@RequestBody Map<String, String> request) {
        String to = request.get("to");
        String warehousingNo = request.get("warehousingNo");
        Date curr = new Date();
        String username = UserUtils.getUserName();
        Warehousing warehousing = new Warehousing();
        warehousing.setWarehousingNo(warehousingNo);
        warehousing.setStatus(to);
        warehousing.setUpdator(username);
        warehousing.setUpdateOn(curr);
        warehousingMapper.updateByPrimaryKeySelective(warehousing);
        return Json.succ();
    }

    @GetMapping
    @RequestMapping("/info")
    public Json getWarehousing(@RequestParam("warehousingNo") String warehousingNo) {
        Warehousing warehousing = warehousingMapper.selectByPrimaryKey(warehousingNo);
        List<WarehousingContent> warehousingContentList = warehousingContentMapper.listContent(warehousingNo);
        warehousing.setWarehousingContentList(warehousingContentList);
        return Json.succ().data(warehousing);
    }

    @GetMapping("/drop")
    public Json dropWarehousing(@RequestParam String warehousingNo) {
        warehousingMapper.deleteByPrimaryKey(warehousingNo);
        warehousingContentMapper.deleteByWarehousingNo(warehousingNo);
        return Json.succ();
    }

    private StringBuilder getErrMsg(BindingResult bindingResult) {
        StringBuilder errMsg = new StringBuilder();
        for (ObjectError objectError : bindingResult.getAllErrors()) {
            errMsg.append(objectError.getDefaultMessage()).append(";");
        }
        return errMsg;
    }

    @PostMapping
    @RequestMapping("/trackno")
    public Json fillInTrackNo(@RequestBody Warehousing warehousing) {
        Date curr = new Date();
        String username = UserUtils.getUserName();
        warehousing.setUpdateOn(curr);
        warehousing.setUpdator(username);
        warehousingMapper.updateByPrimaryKeySelective(warehousing);
        return Json.succ();
    }

    @PostMapping
    @RequestMapping("/printCustomsDeclaration")
    public Json printCustomsDeclaration(@RequestBody CompanyProfile companyProfile) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CompanyProfile dyCompanyProfile = companyProfileMapper.init("dy");
        File file = fileMapper.selectByPrimaryKey("COMMERCIAL_INVOICE_TEMPLATE");
        InputStream templateInputStream = new ByteArrayInputStream(file.getFile());
        List<WarehousingContent> warehousingContentList = warehousingContentMapper
                .listContent(companyProfile.getWarehousingNo());
        customsDeclarationUtil.print(companyProfile, dyCompanyProfile, warehousingContentList, templateInputStream,
                outputStream);
        byte[] fileBytes = outputStream.toByteArray();
        File resultFile = new File(UUID.randomUUID().toString(), fileBytes, "报关单.docx");
        fileMapper.insertWithName(resultFile);
        templateInputStream.close();
        outputStream.close();
        return Json.succ().data(resultFile.getUuid());
    }

    @PostMapping
    @RequestMapping("/userFile")
    public Json uploadFile4Warehousing(@RequestParam(value = "file") MultipartFile multipartFile,
            @RequestParam String warehousingNo) throws IOException {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        File file = new File(uuid, multipartFile.getBytes(), multipartFile.getOriginalFilename());
        fileMapper.insertWithName(file);
        Warehousing warehousing = new Warehousing();
        warehousing.setWarehousingNo(warehousingNo);
        warehousing.setUserWarehousingFileUuid(uuid);
        warehousingMapper.updateByPrimaryKeySelective(warehousing);
        return Json.succ();
    }

}
