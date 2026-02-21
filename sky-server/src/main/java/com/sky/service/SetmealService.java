package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {
    /**
     * 根据分类id查询套餐
     * @param categoryId
     * @return
     */
    List<Setmeal> getByCategoryId(String categoryId);

    /**
     * 套餐分页查询
     * @param setmealDTO
     * @return
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealDTO);

    /**
     * 新增套餐
     * @param setmealDTO
     */
    void add(SetmealDTO setmealDTO);

    /**
     * 套餐起售，停售
     *
     * @param status
     * @param id
     */
    void stopOrStart(String status, Long id);

    /**
     * 根据id查询套餐
     * @param id
     * @return
     */
    SetmealVO getById(Long id);

    /**
     * 修改套餐
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);

    /**
     * 批量删除套餐
     * @param ids
     */
    void deleteByIds(String[] ids);

    /**
     * DishItemVO
     * @param id
     * @return
     */
    List<DishItemVO> getDishes(Long id);
}
