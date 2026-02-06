package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SetmealMapper {

    /**
     * 检查分类是否与套餐关联
     * @param id
     * @return
     */
    @Select("select count(*) from sky_take_out.setmeal where category_id = #{id}")
    Integer countBySetmeal(Long id);
}
