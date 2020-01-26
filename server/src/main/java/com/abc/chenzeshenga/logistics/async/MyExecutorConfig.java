package com.abc.chenzeshenga.logistics.async;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Component
public class MyExecutorConfig {

  @Bean
  public ExecutorService getThreadPool() {
    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("pool-%d").build();
    return new ThreadPoolExecutor(
        5,
        200,
        0L,
        TimeUnit.MILLISECONDS,
        new LinkedBlockingDeque<>(1024),
        namedThreadFactory,
        new ThreadPoolExecutor.AbortPolicy());
  }
}
