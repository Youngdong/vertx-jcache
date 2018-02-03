package com.flycamel.vertxjcache.domain;

import java.util.List;

public interface UserDomainService {
    User getUser(Long userId);

    List<User> getAllUser();
}
