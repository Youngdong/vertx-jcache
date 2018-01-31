package com.flycamel.vertxjcache;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;

@Component
public class HazelcastResolver {
    private CacheManager cacheManagerForResolver;

    @Resource
    public void setCacheManagerForResolver(CacheManager cacheManagerForResolver) {
        this.cacheManagerForResolver = cacheManagerForResolver;
    }

    public void start() {
        System.out.println("Hazelcast start...");

        Cache<String, String> stringCache = cacheManagerForResolver.createCache("stringCache", new MutableConfiguration<>());
        stringCache.put("key1", "value1");

        System.out.println(stringCache.get("key1"));
    }
}
