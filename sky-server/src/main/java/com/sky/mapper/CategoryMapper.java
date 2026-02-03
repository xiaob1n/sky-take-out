package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.Dish;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 分页查询菜品
     * @param categoryPageQueryDTO
     * @return
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 新增菜品分类
     * @param category
     */
    @Insert("insert into sky_take_out.category (type, name, sort, status, create_time, update_time, create_user, update_user) "+
            "values "+
            "(#{type},#{name},#{sort},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void add(Category category);

    /**
     * 根据id禁用，启用菜品分类
     * @param status
     * @param id
     */
    @Update("update sky_take_out.category set status = #{status} where id = #{id}")
    void stopOrStart(Integer status, Long id);

    /**
     * 根据id删除分类
     * @param id
     */
    @Delete("delete from sky_take_out.category where id = #{id}")
    void delete(Long id);

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    @Select("select * from sky_take_out.category where type = #{type}")
    List<Category> getByType(String type);

    /**
     * 修改分类
     * @param category
     */
    void update(Category category);
}
