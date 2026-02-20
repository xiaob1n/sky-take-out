package com.sky.controller.user;

import com.sky.entity.Category;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userCategoryController")
@RequestMapping("/user/category")
@Slf4j
@Api(tags = "用户条件查询相关接口")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据类型查询菜品分类
     * @param type
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据类型查询菜品分类")
    public Result<List<Category>> getByType(Integer type) {
        log.info("根据类型查询菜品分类：{}", type);
        List<Category> categories = categoryService.getByType(type);
        return Result.success(categories);
    }
}
