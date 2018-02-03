package com.flycamel.vertxjcache;

import com.hazelcast.core.HazelcastInstance;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class HazelcastResolver {

    private CacheManager cacheManager;
    private HazelcastInstance hazelcastInstance;
    private ExVerticle exVerticle;
    private HttpVerticle httpVerticle;

    @Resource
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Resource
    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @Resource
    public void setExVerticle(ExVerticle exVerticle) {
        this.exVerticle = exVerticle;
    }

    @Resource
    public void setHttpVerticle(HttpVerticle httpVerticle) {
        this.httpVerticle = httpVerticle;
    }

    public void start() {
        System.out.println("Hazelcast start...");

        ClusterManager mgr = new HazelcastClusterManager(hazelcastInstance);
        VertxOptions options = new VertxOptions().setClusterManager(mgr);
        Vertx.clusteredVertx(options, res -> {
            if (res.succeeded()) {
                Vertx vertx = res.result();
                vertx.deployVerticle(exVerticle, ar -> {
                    System.out.println("exVerticle deployed..");
                    vertx.eventBus().send("test", "test message from resolver");
                });

                vertx.deployVerticle(httpVerticle, ar -> {
                    System.out.println("httpVerticle deployed..");
                });
            }
        });
    }
}
