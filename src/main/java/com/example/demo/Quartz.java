package com.example.demo;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("quartz1")
@EnableScheduling
public class Quartz {
		
	@Scheduled(cron = "0/4 * * * * ?")
	public void exe() {
		System.out.println("-------------");
	}
}
