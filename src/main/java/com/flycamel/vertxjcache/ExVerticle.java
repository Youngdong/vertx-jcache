package com.flycamel.vertxjcache;

import io.vertx.core.AbstractVerticle;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Component
public class ExVerticle extends AbstractVerticle {
    private CacheManager cacheManager;

    @Resource
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer("test", message -> {
            System.out.println("Ex verticle : " + message.body());

            List<String> nicks = Arrays.asList("camel", "flycamel", "hahahi");

            Cache cache = cacheManager.getCache("test_cache");
            cache.put("yd", new MyPojo("yd", nicks));

            System.out.println(cache.get("yd", MyPojo.class));
        });
    }
}
