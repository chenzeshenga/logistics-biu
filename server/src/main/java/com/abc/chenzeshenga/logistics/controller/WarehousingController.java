package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.WarehousingContentMapper;
import com.abc.chenzeshenga.logistics.mapper.WarehousingMapper;
import com.abc.chenzeshenga.logistics.model.ManualOrder;
import com.abc.chenzeshenga.logistics.model.Warehousing;
import com.abc.chenzeshenga.logistics.model.WarehousingContent;
import com.abc.chenzeshenga.logistics.service.WarehousingService;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.entity.SysUser;
import com.abc.util.PageUtils;
import com.abc.vo.AuthVo;
import com.abc.vo.Json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author chenzesheng
 * @version 1.0
 */
@Slf4j @RestController @RequestMapping("/warehousing") public class WarehousingController {

    @Resource private WarehousingMapper warehousingMapper;

    @Resource private WarehousingContentMapper warehousingContentMapper;

    private WarehousingService warehousingService;

    @Autowired public WarehousingController(WarehousingService warehousingService) {
        this.warehousingService = warehousingService;
    }

    @PostMapping @RequestMapping("/add") public Json add(@RequestBody Warehousing warehousing) {
        String username = UserUtils.getUserName();
        warehousing.setCreator(username);
        warehousing.setUpdator(username);
        Date curr = new Date();
        warehousing.setCreateOn(curr);
        warehousing.setUpdateOn(curr);
        warehousing.setStatus("1");
        List<WarehousingContent> warehousingContentList = warehousing.getWarehousingContentList();
        warehousingMapper.insert(warehousing);
        warehousingContentMapper.insertList(warehousingContentList);
        return Json.succ();
    }

    @PostMapping @RequestMapping("/list/{method}/{status}")
    public Json list(@RequestBody String body, @PathVariable String method, @PathVariable String status) {
        String cname = UserUtils.getUserName();
        JSONObject jsonObject = JSON.parseObject(body);
        Page page = PageUtils.getPageParam(jsonObject);
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser)subject.getPrincipal();
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
        return Json.succ().data("page", warehousingPage);
    }

}
