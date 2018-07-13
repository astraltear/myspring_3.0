package com.audien.common.crontab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.audien.common.config.SystemProperties;

@Component
public class Scheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

	
//	http://expert0226.tistory.com/207
//	http://kanetami.tistory.com/entry/Schedule-Spring-%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8A%A4%EC%BC%80%EC%A5%B4-%EC%84%A4%EC%A0%95%EB%B2%95-CronTab
	@Scheduled(cron="*/5 * * * * *")
	public void cronTest1() {
		boolean disabled = SystemProperties.disabled("batch.job.shop.hot_book.disabled");
		logger.info("Scheduler:cronTest1:disabled:"+disabled);
		
		String id = SystemProperties.getId("batch.job.shop.hot_book.id");
		logger.info("Scheduler:cronTest1:id:"+id);
	}
}
