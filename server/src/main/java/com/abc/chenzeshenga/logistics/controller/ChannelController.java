package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.ChannelMapper;
import com.abc.chenzeshenga.logistics.model.Channel;
import com.abc.chenzeshenga.logistics.model.ChannelLabel;
import com.abc.chenzeshenga.logistics.service.ChannelService;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.util.PageUtils;
import com.abc.vo.Json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@RestController @RequestMapping("/channel") @Slf4j public class ChannelController {

    @Resource private ChannelMapper channelMapper;

    private ChannelService channelService;

    @Autowired public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping @RequestMapping("/list") public Json list() {
        List<ChannelLabel> channelList = channelMapper.list();
        return Json.succ().data(channelList);
    }

    @PostMapping @RequestMapping("/add") public Json add(@RequestBody Channel channel) {
        String username = UserUtils.getUserName();
        channel.setCreateBy(username);
        channel.setUpdateBy(username);
        Date curr = new Date();
        channel.setCreateOn(curr);
        channel.setUpdateOn(curr);
        channelMapper.insert(channel);
        return Json.succ().data(channel);
    }

    @GetMapping @RequestMapping("/delete/{channelCode}") public Json add(@PathVariable String channelCode) {
        channelMapper.deleteByPrimaryKey(channelCode);
        return Json.succ().data(channelCode);
    }

    @PostMapping @RequestMapping("/update") public Json update(@RequestBody Channel channel) {
        channel.setUpdateOn(new Date());
        channel.setUpdateBy(UserUtils.getUserName());
        channelMapper.updateByPrimaryKeySelective(channel);
        return Json.succ().data(channel);
    }

    @PostMapping @RequestMapping("/page") public Json page(@RequestBody String body) {
        JSONObject jsonObject = JSON.parseObject(body);
        Page page = PageUtils.getPageParam(jsonObject);
        Page<Channel> channelPage = channelService.list(page);
        return Json.succ().data("page", channelPage);
    }

}
