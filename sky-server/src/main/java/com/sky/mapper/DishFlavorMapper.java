package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /**
     * 插入口味数据
     * @param flavors
     */
    void insert(List<DishFlavor> flavors);

    /**
     * 删除菜品口味
     * @param id
     */
    @Delete("delete from sky_take_out.dish_flavor where dish_id = #{id}")
    void deleteByDishId(Long id);

    /**
     * 根据id查询菜品口味
     * @param id
     * @return
     */
    @Select("select * from sky_take_out.dish_flavor where dish_id = #{id}")
    List<DishFlavor> getByDishId(Long id);

}
