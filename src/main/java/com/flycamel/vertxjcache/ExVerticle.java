package com.flycamel.vertxjcache;

import io.vertx.core.AbstractVerticle;
import org.springframework.stereotype.Component;

@Component
public class ExVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer("test", message -> {
            System.out.println("Ex verticle : " + message.body());
        });
    }
}
