package com.example.demo;
import org.springframework.web.bind.annotation.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

@SpringBootApplication
@ComponentScan("com.example")
public class DemoApplication {

	private final Integer connectionPoolSize;

    // @GetMapping("/")
	// String home(){
	// 	return "Hello World!";
	// }

	@Autowired
	public DemoApplication(
		@Value("${spring.datasource.maximum-pool-size:10}")
		Integer connectionPoolSize
	) {
		this.connectionPoolSize = connectionPoolSize;
	}

    @Bean
    public Scheduler jdbcScheduler() {
        return Schedulers.fromExecutor(Executors.newFixedThreadPool(connectionPoolSize));
    }


	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
		String mysqlUri = ctx.getEnvironment().getProperty("spring.datasource.url");
	}

}
