package com.laurdawn.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.laurdawn.entity.ApiInterface;
import com.laurdawn.entity.CatMenu;
import com.laurdawn.entity.Project;
import com.laurdawn.entity.YapiRes;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.lang.reflect.*;
import java.util.*;

public class YapiUtils {

    private static String QUERY_PROJCET_API = "/api/project/get";

    private static String QUERY_CAT_MENU_API = "/api/interface/getCatMenu";

    public static Project getProjectInfo(String url, String token) throws Exception{
        url += QUERY_PROJCET_API;
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("token", token);
        url = HttpUtils.urlJoinParam(url, paramMap);
        String result =  HttpUtils.doGet(url);
        YapiRes yapiRes = JSON.parseObject(result, new TypeReference<YapiRes>(){});
        JSONObject projectJson = (JSONObject)yapiRes.getData();
        Project project = new Project();
        project.setId((Integer)projectJson.get("_id"));
        project.setName(String.valueOf(projectJson.get("name")));
        project.setDesc(String.valueOf(projectJson.get("desc")));
        return project;
    }

    public static List<CatMenu> getCatMenuInfo(String url, String token, String projectId) throws Exception{
        url += QUERY_CAT_MENU_API;
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("token", token);
        paramMap.put("project_id", projectId);
        url = HttpUtils.urlJoinParam(url, paramMap);
        String result =  HttpUtils.doGet(url);
        YapiRes yapiRes = JSON.parseObject(result, new TypeReference<YapiRes>(){});
        JSONArray catMenuJson = (JSONArray)yapiRes.getData();
        List<CatMenu> catMenuList = new ArrayList<>();
        for(Object object : catMenuJson){
            JSONObject jsonObject = (JSONObject) object;
            CatMenu catMenu = new CatMenu();
            catMenu.setUid(jsonObject.getInteger("uid"));
            catMenu.setId(jsonObject.getInteger("_id"));
            catMenu.setName(jsonObject.getString("name"));
            catMenu.setDesc(jsonObject.getString("desc"));
            catMenuList.add(catMenu);
        }
        return catMenuList;
    }

    public static List<ApiInterface> parseApi(String path) throws Exception{
        List<ApiInterface> apiList = new ArrayList<>();
        Class clazz = Class.forName(path);
        Method[] methods = clazz.getMethods();
        for(Method method: methods){
            ApiInterface apiInterface = new ApiInterface();
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            //获取方法参数
            apiInterface.setTitle(apiOperation.value());
//            if(apiOperation.tags().length > 0){
//                apiInterface.setTag(apiOperation.tags());
//            }
            //获取方法入参
            String req = getParamJsonString(method);
            apiInterface.setReqBodyOther(req);
            //获取方法返参
            String resp = getReturnJsonString(method);
            apiInterface.setResBody(resp);
            apiList.add(apiInterface);
        }
        return apiList;
    }

    private static String getParamJsonString(Method method) throws Exception{
        Parameter[] parameters = method.getParameters();
        Map<String, Object> paramObject = new HashMap<>();
        paramObject.put("type", "object");
        Map<String, Object> propertiesMap = new HashMap<>();
        paramObject.put("properties", propertiesMap);
        for(Parameter parameter: parameters){
            ApiParam apiParam = parameter.getAnnotation(ApiParam.class);
            propertiesMap.put(apiParam.name(), addParameter(parameter, apiParam));
        }
        return JSON.toJSONString(paramObject);
    }

    private static String getReturnJsonString(Method method) throws Exception{
        Map<String, Object> map = new HashMap<>();
        Type type = method.getGenericReturnType();
        switchType(type, map, "");
        return JSON.toJSONString(map);
    }

    private static Map<String, Object> addParameter(Parameter parameter, ApiParam apiParam) throws Exception{
        Map<String, Object> attributeMap = new HashMap<>();
        switchType(parameter.getParameterizedType(), attributeMap, apiParam.value());
        return attributeMap;
    }

    private static void switchType(Type type, Map<String, Object> map, String desc) throws Exception{
        if(type instanceof ParameterizedType){
            ParameterizedType t = (ParameterizedType)type;
            Type rowType = t.getRawType();
            if(rowType.equals(List.class) || type.equals(Set.class)){
                map.put("type", "array");
                HashMap<String, Object> itemMap = new HashMap<>();
                map.put("items", itemMap);
                Type[] types = t.getActualTypeArguments();
                switchType(types[0], itemMap, desc);
            } else if(rowType.equals(Map.class)){
                throw new Exception("不支持解析Map类型");
            }
            return;
        }
        if(type.equals(String.class) || type.equals(Character.class) || type.equals(Date.class)){
            map.put("type", "string");
            map.put("description", desc);
        }else if(type.equals(Short.class) || type.equals(Integer.class)){
            map.put("type", "integer");
            map.put("description", desc);
        }else if(type.equals(Long.class) || type.equals(Double.class) || type.equals(Float.class)){
            map.put("type", "number");
            map.put("description", desc);
        }else if(type.equals(Boolean.class)){
            map.put("type", "boolean");
            map.put("description", desc);
        }else if(((Class) type).isArray()){
            map.put("type", "array");
            HashMap<String, Object> itemMap = new HashMap<>();
            map.put("items", itemMap);
            switchType(((Class) type).getComponentType(), itemMap, desc);
        }else{
            map.put("type", "object");
            map.put("description", desc);
            map.put("properties", entityHandle(type));

        }
    }

    private static Map<String, Object> entityHandle(Type type)throws Exception{
        Map<String, Object> entityMap = new HashMap<>();
        Field[] fields = ((Class) type).getDeclaredFields();
        for(Field field: fields){
            Map<String, Object> attributeMap = new HashMap<>();
            ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
            if(apiModelProperty == null){
                throw new Exception(String.format("[%s]的字段[%s]缺少@ApiModelProperty注解", ((Class) type).getName(), field.getName()));
            }
            switchType(field.getType(), attributeMap, apiModelProperty.value());
            entityMap.put(field.getName(), attributeMap);
        }
        return entityMap;
    }

}
