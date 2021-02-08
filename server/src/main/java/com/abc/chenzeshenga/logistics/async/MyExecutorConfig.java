package com.abc.chenzeshenga.logistics.async;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Component
public class MyExecutorConfig {

  @Bean
  public ExecutorService getThreadPool() {
    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("logistics-pool-%d").build();
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
