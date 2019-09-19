package com.abc.chenzeshenga.logistics.cache;

import com.abc.chenzeshenga.logistics.mapper.ChannelMapper;
import com.abc.chenzeshenga.logistics.model.ChannelLabel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
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

    private String channelSeq;

    @Scheduled(cron = "0 0 0 * * ?") @PostConstruct public void init() {
        List<ChannelLabel> channelLabelList = channelMapper.list();
        channelLabelList.forEach(channelLabel -> channelLabelCache.put(channelLabel.getValue(), channelLabel));
        if (channelLabelList.isEmpty()) {
            this.channelSeq = "0";
        } else {
            this.channelSeq = String.valueOf(
                Integer.parseInt(channelLabelList.get(channelLabelList.size() - 1).getValue().replace("CHANNEL_", ""))
                    + 1);
        }
    }

    public String channelLabel(String channelValue) {
        ChannelLabel channelLabel = channelLabelCache.get(channelValue);
        if (ObjectUtils.allNotNull(channelLabel)) {
            return channelLabel.getLabel();
        } else {
            return "";
        }
    }

    public String getChannelSeq() {
        String result = this.channelSeq;
        this.channelSeq = String.valueOf(Integer.valueOf(this.channelSeq) + 1);
        return result;
    }

}
