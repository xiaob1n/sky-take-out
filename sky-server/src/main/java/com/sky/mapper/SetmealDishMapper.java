package com.sky.mapper;

import com.sky.entity.SetmealDish;
import com.sky.vo.DishItemVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /**
     * 检查菜品是否与套餐关联
     * @param id
     * @return
     */
    @Select("select count(*) from sky_take_out.setmeal_dish where dish_id = #{id}")
    Integer countByDishId(Long id);

    /**
     * 插入菜品套餐表
     * @param setmealDishes
     */
    void insert(List<SetmealDish> setmealDishes);

    /**
     * 根据套餐id查询套餐
     * @param id
     * @return
     */
    @Select("select * from sky_take_out.setmeal_dish where setmeal_id = #{id}")
    List<SetmealDish> getBySetmealId(Long id);

    /**
     * 删除套餐相关菜品
     * @param setmealId
     */
    @Delete("delete from sky_take_out.setmeal_dish where setmeal_id = #{setmealId}")
    void deleteBySetmealId(Long setmealId);

    /**
     * 查询套餐菜品及其及其描述
     *
     * @param id
     * @return
     */
    List<DishItemVO> getBySetmealIdWithDescription(Long id);

}
