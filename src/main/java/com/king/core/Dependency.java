package com.king.core;

/**
 * Created by king on 2017/5/2.
 */
public class Dependency {
    String groupId;
    String artifactId;
    String version;

    public Dependency() {
    }

    public Dependency(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }
}
