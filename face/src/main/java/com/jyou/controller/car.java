package com.jyou.controller;

/**
 * @Author: RenXin
 * @Date: Created in 2018/6/20
 * @Description:
 */
public class car {
    private int id ;
    private String name;

    public car() {
    }

    public car(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
