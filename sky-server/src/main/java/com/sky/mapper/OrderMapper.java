package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderMapper {
    /**
     * 插入订单表
     * @param order
     */
    void insert(Orders order);

    /**
     * 根据订单查询订单号
     * @param outTradeNo
     * @return
     */
    @Select("select * from sky_take_out.orders where number = #{orderNumber}")
    Orders getByNumber(String outTradeNo);

    /**
     * 修改订单
     * @param orders
     */
    void update(Orders orders);

    /**
     * 根据用户id查询订单
     * @param
     * @return
     */
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 根据id查询订单
     * @param id
     * @return
     */
    @Select("select * from sky_take_out.orders where id = #{id};")
    Orders getById(Long id);

    /**
     * 各个状态的订单数量统计
     * @param status
     * @return
     */
    @Select("select count(*) from sky_take_out.orders where status = #{status}")
    Integer countByStatus(Integer status);
}
