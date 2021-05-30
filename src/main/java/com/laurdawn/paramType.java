package com.laurdawn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public enum paramType {


    STRING("string", String.class),

    NUMBER_1("number", Integer.class),
    NUMBER_2("number", Double.class),
    NUMBER_3("number", Long.class),
    NUMBER_4("number", Float.class),

    ARRAY("array", Collection.class),

    OBJECT("object", Object.class),

    BOOLEAN("boolean", Boolean.class),

    ;

    paramType(java.lang.String type, Class javaType) {
        this.type = type;
        this.javaType = javaType;
    }

    /**
     * 参数对应yapi类型
     */
    private String type;

    /**
     * 对应的java类型
     */
    private Class javaType;

    public java.lang.String getType() {
        return type;
    }

    public void setType(java.lang.String type) {
        this.type = type;
    }

    public Class getJavaType() {
        return javaType;
    }

    public void setJavaType(Class javaType) {
        this.javaType = javaType;
    }
}
