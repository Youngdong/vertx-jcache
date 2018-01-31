package com.flycamel.vertxjcache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;

@Configuration
public class HazelcastConfig {
    @Bean
    CacheManager cacheManagerForResolver() {
        return Caching.getCachingProvider().getCacheManager();
    }
}
