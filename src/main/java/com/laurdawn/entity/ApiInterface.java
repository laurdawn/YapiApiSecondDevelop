package com.laurdawn.entity;

import java.util.List;

public class ApiInterface {
    /**
     * 项目token
     */
    private String token;

    /**
     * 接口名称
     */
    private String title;

    /**
     * 分类id
     */
    private Integer catid;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 接口路径
     */
    private String path;

    /**
     * 标签
     */
//    private String[] tag;

    /**
     * 是否完成["done":已完成;"undone"未完成]
     */
    private String status;

    /**
     * 请求头
     */
    private List<ReqHeaders> reqHeaders;

    /**
     * 请求json参数
     */
    private String reqBodyOther;

    /**
     * 返回参数
     */
    private String resBody;

    private Boolean reqBodyIsJsonSchema = true;

    private Boolean resBodyIsJsonSchema = true;

    private String resBodyType = "json";

    private String reqBodyType = "json";

    private String type = "static";

    public Integer getCatid() {
        return catid;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
//
//    public String[] getTag() {
//        return tag;
//    }
//
//    public void setTag(String[] tag) {
//        this.tag = tag;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ReqHeaders> getReqHeaders() {
        return reqHeaders;
    }

    public void setReqHeaders(List<ReqHeaders> reqHeaders) {
        this.reqHeaders = reqHeaders;
    }

    public Boolean getReqBodyIsJsonSchema() {
        return reqBodyIsJsonSchema;
    }

    public void setReqBodyIsJsonSchema(Boolean reqBodyIsJsonSchema) {
        this.reqBodyIsJsonSchema = reqBodyIsJsonSchema;
    }

    public Boolean getResBodyIsJsonSchema() {
        return resBodyIsJsonSchema;
    }

    public void setResBodyIsJsonSchema(Boolean resBodyIsJsonSchema) {
        this.resBodyIsJsonSchema = resBodyIsJsonSchema;
    }

    public String getResBodyType() {
        return resBodyType;
    }

    public void setResBodyType(String resBodyType) {
        this.resBodyType = resBodyType;
    }

    public String getReqBodyType() {
        return reqBodyType;
    }

    public void setReqBodyType(String reqBodyType) {
        this.reqBodyType = reqBodyType;
    }

    public String getReqBodyOther() {
        return reqBodyOther;
    }

    public void setReqBodyOther(String reqBodyOther) {
        this.reqBodyOther = reqBodyOther;
    }

    public String getResBody() {
        return resBody;
    }

    public void setResBody(String resBody) {
        this.resBody = resBody;
    }

    @Override
    public String toString() {
        return "ApiInterface{" +
                "token='" + token + '\'' +
                ", title='" + title + '\'' +
                ", catid=" + catid +
                ", projectId=" + projectId +
                ", method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", status='" + status + '\'' +
                ", reqHeaders=" + reqHeaders +
                ", reqBodyOther='" + reqBodyOther + '\'' +
                ", resBody='" + resBody + '\'' +
                ", reqBodyIsJsonSchema=" + reqBodyIsJsonSchema +
                ", resBodyIsJsonSchema=" + resBodyIsJsonSchema +
                ", resBodyType='" + resBodyType + '\'' +
                ", reqBodyType='" + reqBodyType + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
