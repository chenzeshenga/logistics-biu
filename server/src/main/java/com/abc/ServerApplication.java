package com.abc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * main class to start server
 * @author CaiBaoHong
 */
@SpringBootApplication(scanBasePackages = {"com.abc"})
@EnableScheduling
public class ServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServerApplication.class, args);
  }
}
