package com.laurdawn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.laurdawn.entity.ApiInterface;
import com.laurdawn.entity.CatMenu;
import com.laurdawn.entity.Project;
import com.laurdawn.entity.ReqHeaders;
import com.laurdawn.utils.HttpUtils;
import com.laurdawn.utils.YapiUtils;

import java.util.ArrayList;
import java.util.List;

public class Test {

    private static String url = "https://yapi.baidu.com";

    private static String token = "71e375b4bcd804bfa6c8427800b58c203df703d8d74c599df0d811e894201579";

    private static String catName = "文件管理";

    public static void main(String[] args) {
        Project project = null;
        CatMenu catMenu = null;
        try {
            project = YapiUtils.getProjectInfo(url, token);
            List<CatMenu> catMenus = YapiUtils.getCatMenuInfo(url, token, String.valueOf(project.getId()));
            for(CatMenu item: catMenus){
                if(catName.equals(item.getName())){
                    catMenu = item;
                    break;
                }
            }
            List<ApiInterface> list = YapiUtils.parseApi("com.laurdawn.TestApi");
            SerializeConfig config = new SerializeConfig();
            config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
            for(ApiInterface apiInterface: list){
                apiInterface.setToken(token);
                apiInterface.setCatid(catMenu.getId());
                apiInterface.setProjectId(project.getId());
                apiInterface.setMethod("POST");
                apiInterface.setPath("/dw/Workbench/v1/fileManage/test");
                List<ReqHeaders> reqHeadersList = new ArrayList<>();
                reqHeadersList.add(new ReqHeaders());
//                apiInterface.setStatus("undone");
//                apiInterface.setTag(null);
                apiInterface.setReqHeaders(reqHeadersList);
                String info = JSON.toJSONString(apiInterface, config);
                String result = HttpUtils.doPost("https://yapi.baidu.com/api/interface/save", info);
                System.out.println(String.format("Yapi接口管理：项目[%s],分组[%s],新增或更新接口[%s]：%s", project.getName(), catMenu.getName(), apiInterface.getTitle(), apiInterface.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
