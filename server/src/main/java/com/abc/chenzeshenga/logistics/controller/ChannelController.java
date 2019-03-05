package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.cache.LabelCache;
import com.abc.chenzeshenga.logistics.mapper.ChannelMapper;
import com.abc.chenzeshenga.logistics.model.Channel;
import com.abc.chenzeshenga.logistics.model.Label;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController @RequestMapping("/api/v1/channel") @Slf4j public class ChannelController {

    @Resource private ChannelMapper channelMapper;

    private LabelCache labelCache;

    @Autowired public ChannelController(LabelCache labelCache) {
        this.labelCache = labelCache;
    }

    @GetMapping @RequestMapping("/list") public ResponseEntity<List<Channel>> list() {
        List<Channel> channelList = channelMapper.list();
        for (Channel channel : channelList) {
            channel.setCooperatorName(labelCache.getLabel("cooperator_" + channel.getCooperatorCode()));
            channel.setCurrencyName(labelCache.getLabel("currency_" + channel.getCurrencyCode()));
            channel.setWeightRuleDesc(labelCache.getLabel("weightRule_" + channel.getWeightRuleCode()));
            channel.setFeeRuleName(labelCache.getLabel("feeRule_" + channel.getFeeRuleCode()));
        }
        return new ResponseEntity<>(channelList, HttpStatus.OK);
    }

}
