package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    /**
     * 插入订单明细表
     * @param orderDetail
     */
    @Insert("insert into sky_take_out.order_detail (name, image, order_id, dish_id, setmeal_id, dish_flavor, amount) VALUES " +
            "(#{name}, #{image}, #{orderId}, #{dishId}, #{setmealId}, #{dishFlavor}, #{amount})")
    void insert(OrderDetail orderDetail);

    /**
     * 根据订单id查询菜品
     * @param orderId
     * @return
     */
    @Select("select * from sky_take_out.order_detail where order_id = #{orderId};")
    List<OrderDetail> getByOrderId(Long orderId);
}
