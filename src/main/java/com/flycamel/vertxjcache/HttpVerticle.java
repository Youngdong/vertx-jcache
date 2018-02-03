package com.flycamel.vertxjcache;

import com.flycamel.vertxjcache.domain.User;
import com.flycamel.vertxjcache.domain.UserDomainService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class HttpVerticle extends AbstractVerticle {

    private UserDomainService userDomainService;

    @Resource
    public void setUserDomainService(UserDomainService userDomainService) {
        this.userDomainService = userDomainService;
    }

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.post("/user/getUser").handler(this::getUser);
        router.get("/user/getAllUser").handler(this::getAllUser);

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8081);
    }

    private void getAllUser(RoutingContext rc) {
        List<User> users = userDomainService.getAllUser();

        rc.response()
                .setStatusCode(200)
                .putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .end(Json.encode(users));
    }

    private void getUser(RoutingContext rc) {
        Long userId = Long.parseLong(rc.request().getParam("userId"));
        User user = userDomainService.getUser(userId);

        rc.response()
                .setStatusCode(200)
                .putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .end(Json.encode(user));
    }
}
