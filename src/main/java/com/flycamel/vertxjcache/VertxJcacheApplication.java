package com.flycamel.vertxjcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication
public class VertxJcacheApplication {
    private HazelcastResolver hazelcastResolver;

    @Resource
    public void setHazelcastResolver(HazelcastResolver hazelcastResolver) {
        this.hazelcastResolver = hazelcastResolver;
    }

    public static void main(String[] args) {
		SpringApplication.run(VertxJcacheApplication.class, args);
	}

	@PostConstruct
    public void run() {
        hazelcastResolver.start();
    }
}
