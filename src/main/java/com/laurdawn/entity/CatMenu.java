package com.laurdawn.entity;

import io.swagger.annotations.ApiModelProperty;

public class CatMenu {

    @ApiModelProperty("这是uid")
    private Integer uid;

    @ApiModelProperty("这是id")
    private Integer id;

    @ApiModelProperty("这是name")
    private String name;

    @ApiModelProperty("这是desc")
    private String desc;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
