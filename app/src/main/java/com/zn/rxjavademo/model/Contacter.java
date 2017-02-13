package com.zn.rxjavademo.model;

/**
 * Created by zn on 17/2/13.
 */

public class Contacter {

    private String name;

    public Contacter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Contacter{" +
                "name='" + name + '\'' +
                '}';
    }
}
