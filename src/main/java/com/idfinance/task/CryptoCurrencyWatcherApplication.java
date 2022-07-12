package com.idfinance.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CryptoCurrencyWatcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoCurrencyWatcherApplication.class, args);
        log.info("[CryptoCurrency watcher] Application started");
    }
}
