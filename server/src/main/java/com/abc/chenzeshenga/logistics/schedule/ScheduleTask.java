package com.abc.chenzeshenga.logistics.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author chenzesheng
 * @version 1.0
 */
@Service @Slf4j public class ScheduleTask {

    @Scheduled(fixedDelay = 120000) public void countProduct() {
        log.info("scheduled task {} begin", "countProduct");
    }

}
