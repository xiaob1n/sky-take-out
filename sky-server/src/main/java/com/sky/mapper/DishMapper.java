package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 检查菜品与菜品分类是否有关联
     * @param id
     * @return
     */
    @Select("select count(*) from sky_take_out.dish where category_id = #{id}")
    Integer countByCategoryId(Long id);

    /**
     * 新增菜品
     * @param dish
     */
    @AutoFill(OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 查询菜品在售状态
     * @param id
     * @return
     */
    @Select("select status from sky_take_out.dish where id = #{id}")
    Integer getStatusById(Long id);

    /**
     * 删除菜品
     * @param id
     */
    @Delete("delete from sky_take_out.dish where id = #{id}")
    void deleteById(Long id);

    /**
     * 菜品起售，停售
     */
    @AutoFill(OperationType.UPDATE)
    void update(Dish dish);

    /**
     * 根据id查找菜品
     * @param id
     * @return
     */
    @Select("select * from sky_take_out.dish where id = #{id}")
    Dish getById(Long id);

    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @param status
     * @return
     */
    @Select("select * from sky_take_out.dish where category_id = #{categoryId} and status = #{status}")
    List<Dish> getByCategoryId(String categoryId, Integer status);

    /**
     * 根据状态查询数量
     * @param status
     * @return
     */
    @Select("select count(*) from sky_take_out.dish where status = #{status};")
    Integer countByStatus(Integer status);
}
