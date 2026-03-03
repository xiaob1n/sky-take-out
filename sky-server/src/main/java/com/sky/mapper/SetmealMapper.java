package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {

    /**
     * 检查分类是否与套餐关联
     * @param id
     * @return
     */
    @Select("select count(*) from sky_take_out.setmeal where category_id = #{id}")
    Integer countBySetmeal(Long id);

    /**
     * 根据分类id查询套餐
     * @param categoryId
     * @return
     */
    @Select("select * from sky_take_out.setmeal where category_id = #{categoryId}")
    List<Setmeal> getByCategoryId(String categoryId);

    /**
     * 套餐分页查询
     * @return
     */
    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 插入套餐表
     * @param setmeal
     */
    @AutoFill(OperationType.INSERT)
    Long insert(Setmeal setmeal);

    /**
     * 套餐起售，停售
     *
     * @param setmeal
     */
    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);

    /**
     * 根据id查询套餐
     * @param id
     * @return
     */
    @Select("select * from sky_take_out.setmeal where id = #{id}")
    Setmeal getById(Long id);

    /**
     * 批量删除套餐
     * @param id
     */
    @Delete("delete from sky_take_out.setmeal where id = #{id}")
    void deleteById(String id);

    /**
     * 查询套餐总览
     *
     * @return
     */
    @Select("select count(*) from sky_take_out.setmeal where status = #{status}")
    Integer countByStatus(Integer status);
}
