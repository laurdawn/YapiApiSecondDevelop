package com.laurdawn.entity;

public class ReqHeaders {

    private String required = "1";

    private String name = "Content-Type";

    private String value = "application/json";

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
