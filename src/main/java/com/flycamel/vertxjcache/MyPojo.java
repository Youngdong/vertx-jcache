package com.flycamel.vertxjcache;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
public class MyPojo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private List<String> nicknames;
}
