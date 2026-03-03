package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.GoodsSalesDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * 插入订单表
     *
     * @param order
     */
    void insert(Orders order);

    /**
     * 根据订单查询订单号
     *
     * @param outTradeNo
     * @return
     */
    @Select("select * from sky_take_out.orders where number = #{orderNumber}")
    Orders getByNumber(String outTradeNo);

    /**
     * 修改订单
     *
     * @param orders
     */
    void update(Orders orders);

    /**
     * 根据用户id查询订单
     *
     * @param
     * @return
     */
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 根据id查询订单
     *
     * @param id
     * @return
     */
    @Select("select * from sky_take_out.orders where id = #{id};")
    Orders getById(Long id);

    /**
     * 各个状态的订单数量统计
     *
     * @param status
     * @return
     */
    @Select("select count(*) from sky_take_out.orders where status = #{status}")
    Integer countByStatus(Integer status);

    /**
     * 筛选超时订单
     *
     * @param status
     * @param orderTime
     * @return
     */
    @Select("select * from sky_take_out.orders where status = #{status} and order_time < #{orderTime};")
    List<Orders> getByStatusAndOrderTimeLT(Integer status, LocalDateTime orderTime);

    /**
     * 根据订单号查询订单
     *
     * @param orderNumber
     * @return
     */
    @Select("select * from sky_take_out.orders where number = #{orderNumber};")
    Orders getByOrderNumber(String orderNumber);

    /**
     * 根据日期查询当天总营业额
     *
     * @param date
     * @return
     */
    @Select("select sum(amount) from sky_take_out.orders where order_time like concat(#{date},'%') and status = 5;")
    Double sumByDate(LocalDate date);

    /**
     * 获取订单总数
     *
     * @return
     */
    @Select("select count(*) from sky_take_out.orders;")
    Integer getTotalOrders();

    /**
     * 获取有效订单数
     *
     * @return
     */
    @Select("select count(*) from sky_take_out.orders where status = 5;")
    Integer getValidOrder();

    /**
     * 根据日期查询订单数
     *
     * @param date
     * @return
     */
    @Select("select count(*) from sky_take_out.orders where order_time like concat(#{date},'%');")
    Integer countByDate(LocalDate date);

    /**
     * 根据日期查询有效订单
     *
     * @param date
     * @return
     */
    @Select("select count(*) from sky_take_out.orders where order_time like concat(#{date},'%') and status = 5;")
    Integer countValidByDate(LocalDate date);

    /**
     * 统计销量前十的菜品
     * @param begin
     * @param end
     * @return
     */
    @Select("select od.name,count(od.name) number " +
            "from sky_take_out.order_detail od, " +
            "sky_take_out.orders o " +
            "where od.order_id = o.id and o.order_time between #{begin} and #{end} and o.status = 5 " +
            "group by od.name " +
            "order by count(od.name) desc;")
    List<GoodsSalesDTO> getTop10(LocalDate begin, LocalDate end);

    /**
     * 统计订单数
     * @return
     */
    @Select("select count(*) from sky_take_out.orders;")
    Integer count();
}
