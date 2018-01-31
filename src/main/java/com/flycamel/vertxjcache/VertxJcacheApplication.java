package com.flycamel.vertxjcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication(exclude = {HazelcastAutoConfiguration.class})
@EnableCaching
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
