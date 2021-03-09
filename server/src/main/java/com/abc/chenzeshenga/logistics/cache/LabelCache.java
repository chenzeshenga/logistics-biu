package com.abc.chenzeshenga.logistics.cache;

import com.abc.chenzeshenga.logistics.mapper.LabelMapper;
import com.abc.chenzeshenga.logistics.model.Label;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author chenzeshenga
 * @version 1.0
 * @file LabelCache
 * @email chenzeshenga@163.com
 * @createOn 2019-3-6 0:32
 * @updateOn 2019-3-6 0:32
 */
@Component
public class LabelCache {

    @Resource private LabelMapper labelMapper;

    private Map<String, String> labelCache = new HashMap<>(16);

    @Scheduled(cron = "0 0 0 * * ?")
    @PostConstruct
    public void init() {
        List<Label> labelList = labelMapper.list();
        for (Label label : labelList) {
            labelCache.put(label.getKey(), label.getValue());
        }
    }

    public String getLabel(String key) {
        return labelCache.get(key);
    }

    /**
     * @param keyPattern would be like sth like "label_", the underline "_" is required
     * @return
     */
    public List<Label> getLabelList(String keyPattern) {
        List<Label> labelList = new ArrayList<>(16);
        for (Map.Entry<String, String> entry : labelCache.entrySet()) {
            if (entry.getKey().startsWith(keyPattern)) {
                labelList.add(
                        new Label(
                                0,
                                Integer.valueOf(entry.getKey().replace(keyPattern, "")),
                                entry.getKey(),
                                entry.getValue(),
                                entry.getValue()));
            }
        }
        return labelList;
    }
}
