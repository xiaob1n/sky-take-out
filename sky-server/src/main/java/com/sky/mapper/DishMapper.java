package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DishMapper {

    /**
     * 检查菜品与菜品分类是否有关联
     * @param id
     * @return
     */
    @Select("select count(*) from sky_take_out.dish where category_id = #{id}")
    Integer countByCategoryId(Long id);
}
