package com.syncAsync.syncAsync;



import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final TestService testService;

    @GetMapping("/sync")
    public String sync() throws InterruptedException {
        long start = System.currentTimeMillis();
        String result = testService.syncTask();
        long end = System.currentTimeMillis();
        return "[SYNC] 응답: " + result + " / 처리시간: " + (end - start) + "ms";
    }

    @GetMapping("/async")
    public CompletableFuture<String> async() throws InterruptedException {
        long start = System.currentTimeMillis();
        CompletableFuture<String> result = testService.asyncTask();
        long end = System.currentTimeMillis();
        return CompletableFuture.completedFuture("[ASYNC] 응답 예약됨 / API 반환까지 걸린 시간: " + (end - start) + "ms");
    }
}
