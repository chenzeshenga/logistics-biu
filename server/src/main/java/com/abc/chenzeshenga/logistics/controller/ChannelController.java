package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.cache.ChannelCache;
import com.abc.chenzeshenga.logistics.cache.LabelCache;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@RestController @RequestMapping("/channel") @Slf4j public class ChannelController {

    @Resource private ChannelMapper channelMapper;

    private ChannelService channelService;

    private ChannelCache channelCache;

    private LabelCache labelCache;

    @Autowired public ChannelController(ChannelService channelService, ChannelCache channelCache, LabelCache labelCache) {
        this.channelService = channelService;
        this.channelCache = channelCache;
        this.labelCache = labelCache;
    }

    @GetMapping @RequestMapping("/list") public Json list() {
        List<ChannelLabel> channelList = channelMapper.list();
        channelList.forEach(channelLabel -> channelLabel.setPartnerDesc(labelCache.getLabel("carrier_" + channelLabel.getPartner())));
        return Json.succ().data(channelList);
    }

    @GetMapping @RequestMapping("/list/{where}") public Json listByWhere(@PathVariable String where) {
        List<ChannelLabel> channelList = channelMapper.listByWhere(where);
        return Json.succ().data(channelList);
    }

    @PostMapping @RequestMapping("/add") public Json add(@RequestBody Channel channel) {
        String username = UserUtils.getUserName();
        channel.setCreateBy(username);
        channel.setUpdateBy(username);
        channel.setChannelCode("CHANNEL_" + channelCache.getChannelSeq());
        Date curr = new Date();
        channel.setCreateOn(curr);
        channel.setUpdateOn(curr);
        channel.setCheckedRules(String.join(",", channel.getCheckedRules2()));
        channelMapper.insert(channel);
        channelCache.init();
        return Json.succ().data(channel);
    }

    @GetMapping @RequestMapping("/delete/{channelCode}") public Json add(@PathVariable String channelCode) {
        channelMapper.deleteByPrimaryKey(channelCode);
        return Json.succ().data(channelCode);
    }

    @PostMapping @RequestMapping("/update") public Json update(@RequestBody Channel channel) {
        channel.setCheckedRules(String.join(",", channel.getCheckedRules2()));
        channel.setUpdateOn(new Date());
        channel.setUpdateBy(UserUtils.getUserName());
        channelMapper.updateByPrimaryKeySelective(channel);
        return Json.succ().data(channel);
    }

    @PostMapping @RequestMapping("/page") public Json page(@RequestBody String body) {
        JSONObject jsonObject = JSON.parseObject(body);
        Page page = PageUtils.getPageParam(jsonObject);
        Page<Channel> channelPage = channelService.list(page);
        List<Channel> channelList = channelPage.getRecords();
        for (Channel channel : channelList) {
            String checkedRules = channel.getCheckedRules();
            String[] checkedRulesList = checkedRules.split(",");
            StringBuilder stringBuilder = new StringBuilder();
            for (String str : checkedRulesList) {
                stringBuilder.append(";").append(labelCache.getLabel(str));
            }
            channel.setRuleDesc(stringBuilder.toString().replaceFirst(";", ""));
            channel.setPartnerDesc(labelCache.getLabel("carrier_" + channel.getPartner()));
            channel.setCalculateRuleDesc(labelCache.getLabel("rule_" + channel.getRule()));
            channel.setAdapterDesc(labelCache.getLabel("category_" + channel.getAdapter()));
        }
        return Json.succ().data("page", channelPage);
    }

    @PostMapping @RequestMapping("/enable") public Json quickEnable(@RequestBody Map<String, String> channelMap) {
        Channel channel = new Channel();
        channel.setChannelCode(channelMap.get("channelCode"));
        channel.setActive("Y");
        channelMapper.updateByPrimaryKeySelective(channel);
        return Json.succ();
    }

    @PostMapping @RequestMapping("/disable") public Json disable(@RequestBody Map<String, String> channelMap) {
        Channel channel = new Channel();
        channel.setChannelCode(channelMap.get("channelCode"));
        channel.setActive("N");
        channelMapper.updateByPrimaryKeySelective(channel);
        return Json.succ();
    }

    @PostMapping @RequestMapping("/enable/list") public Json quickEnableList(@RequestBody List<String> channelCodes) {
        channelMapper.quickEnableList(channelCodes);
        return Json.succ();
    }

    @PostMapping @RequestMapping("/disable/list") public Json quickDisableList(@RequestBody List<String> channelCodes) {
        channelMapper.quickDisableList(channelCodes);
        return Json.succ();
    }

}
