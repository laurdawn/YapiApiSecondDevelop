package com.laurdawn;

import com.laurdawn.entity.CatMenu;
import com.laurdawn.entity.Project;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

@Api("testApi")
public interface TestApi {

    @ApiOperation(value="用户登录",notes="随边说点啥")
    List<Project> TestA(@ApiParam(value = "id数组", name = "ids", required = true) Integer[] ids,
            @ApiParam(value = "分类菜单", name = "catMenu", required = true) CatMenu catMenu,
                        @ApiParam(value = "主键", name = "id", required = true) Integer id,
                        @ApiParam(value = "字符串测试", name = "name", required = true) String name,
                        @ApiParam(value = "布尔测试", name = "sex", required = true) Boolean sex);
}
