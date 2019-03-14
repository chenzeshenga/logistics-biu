package com.abc.chenzeshenga.logistics.cache;

import com.abc.chenzeshenga.logistics.mapper.ChannelMapper;
import com.abc.chenzeshenga.logistics.model.ChannelLabel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Slf4j @Component public class ChannelCache {

    @Resource private ChannelMapper channelMapper;

    private Map<String, ChannelLabel> channelLabelCache = new HashMap<>();

    @Scheduled(cron = "0 0 0 * * ?") @PostConstruct public void init() {
        List<ChannelLabel> channelLabelList = channelMapper.list();
        channelLabelList.forEach(channelLabel -> channelLabelCache.put(channelLabel.getValue(), channelLabel));
    }

    public String channelLabel(String channelValue) {
        return channelLabelCache.get(channelValue).getLabel();
    }

}
