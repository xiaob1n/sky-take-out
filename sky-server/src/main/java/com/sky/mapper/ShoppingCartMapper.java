package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.ShoppingCart;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    /**
     * 查询是否存在菜品
     * @param shoppingCart
     * @return
     */
    List<ShoppingCart> list(ShoppingCart shoppingCart);

    /**
     * 修改菜品数量
     * @param cart
     */
    void updateNumber(ShoppingCart cart);

    /**
     * 插入菜品
     * @param shoppingCart
     */
    void insert(ShoppingCart shoppingCart);

    /**
     * 清空购物车
     * @param userId
     */
    @Delete("delete from sky_take_out.shopping_cart where user_id = #{userId}")
    void delete(Long userId);

    /**
     * 查询菜品数量
     * @param userId
     * @return
     */
    @Select("select count(*) from sky_take_out.shopping_cart where user_id = #{userId}")
    Integer countByUserId(Long userId);
}
