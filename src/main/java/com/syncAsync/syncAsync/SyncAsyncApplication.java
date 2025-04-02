package com.syncAsync.syncAsync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SyncAsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(SyncAsyncApplication.class, args);
	}

}
