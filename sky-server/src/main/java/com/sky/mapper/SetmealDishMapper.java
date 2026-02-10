package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SetmealDishMapper {

    /**
     * 检查菜品是否与套餐关联
     * @param id
     * @return
     */
    @Select("select count(*) from sky_take_out.setmeal_dish where dish_id = #{id}")
    Integer countByDishId(Long id);

}
