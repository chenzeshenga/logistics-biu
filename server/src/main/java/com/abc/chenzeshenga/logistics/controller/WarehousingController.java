package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.WarehousingContentMapper;
import com.abc.chenzeshenga.logistics.mapper.WarehousingMapper;
import com.abc.chenzeshenga.logistics.model.Warehousing;
import com.abc.chenzeshenga.logistics.model.WarehousingContent;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.vo.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author chenzesheng
 * @version 1.0
 */
@Deprecated
@Slf4j @RestController @RequestMapping("/warehousing") public class WarehousingController {

    @Resource private WarehousingMapper warehousingMapper;

    @Resource private WarehousingContentMapper warehousingContentMapper;

    @PostMapping @RequestMapping("/add") public Json add(@RequestBody Warehousing warehousing) {
        String username = UserUtils.getUserName();
        warehousing.setCreator(username);
        warehousing.setUpdator(username);
        Date curr = new Date();
        warehousing.setCreateOn(curr);
        warehousing.setUpdateOn(curr);
        List<WarehousingContent> warehousingContentList = warehousing.getWarehousingContentList();
        warehousingMapper.insert(warehousing);
        warehousingContentMapper.insertList(warehousingContentList);
        return Json.succ();
    }

}
