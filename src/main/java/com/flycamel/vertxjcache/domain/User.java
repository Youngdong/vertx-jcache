package com.flycamel.vertxjcache.domain;

import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastLogin;

    @Version
    private Long version;
}
