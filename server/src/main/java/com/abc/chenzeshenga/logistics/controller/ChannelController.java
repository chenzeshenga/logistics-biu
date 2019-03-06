package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.ChannelMapper;
import com.abc.chenzeshenga.logistics.model.ChannelLabel;
import com.abc.vo.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 * @file ChannelController
 * @email chenzeshenga@163.com
 * @createOn 2019-3-6 0:46
 * @updateOn 2019-3-6 0:46
 */
@RestController @RequestMapping("/channel") @Slf4j public class ChannelController {

    @Resource private ChannelMapper channelMapper;

    @GetMapping @RequestMapping("/list") public Json list() {
        List<ChannelLabel> channelList = channelMapper.list();
        return Json.succ().data(channelList);
    }

}
