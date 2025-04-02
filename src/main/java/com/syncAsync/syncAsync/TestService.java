package com.syncAsync.syncAsync;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class TestService {
    public String syncTask() throws InterruptedException {
        log.info("SYNC START: {}", Thread.currentThread().getName());
        Thread.sleep(3000);
        log.info("SYNC END");
        return "Sync Done";
    }

    @Async
    public CompletableFuture<String> asyncTask() throws InterruptedException {
        log.info("ASYNC START: {}", Thread.currentThread().getName());
        Thread.sleep(3000);
        log.info("ASYNC END");
        return CompletableFuture.completedFuture("Async Done");
    }
}
