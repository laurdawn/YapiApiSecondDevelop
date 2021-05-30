package com.laurdawn.entity;

import io.swagger.annotations.ApiModelProperty;

public class Project {

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("项目名称")
    private String name;

    @ApiModelProperty("项目描述")
    private String desc;

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
