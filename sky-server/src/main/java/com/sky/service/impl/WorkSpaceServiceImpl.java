package com.sky.service.impl;

import com.sky.constant.StatusConstant;
import com.sky.entity.Orders;
import com.sky.mapper.DishMapper;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.UserMapper;
import com.sky.service.SetmealService;
import com.sky.service.WorkSpaceService;
import com.sky.vo.BusinessDataVO;
import com.sky.vo.DishOverViewVO;
import com.sky.vo.OrderOverViewVO;
import com.sky.vo.SetmealOverViewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class WorkSpaceServiceImpl implements WorkSpaceService {

    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询套餐总览
     *
     * @return
     */
    @Override
    public SetmealOverViewVO overviewSetmeals() {
        Integer sold = setmealMapper.countByStatus(StatusConstant.ENABLE);
        Integer discontinued = setmealMapper.countByStatus(StatusConstant.DISABLE);
        SetmealOverViewVO setmealOverViewVO = SetmealOverViewVO.builder().sold(sold).discontinued(discontinued).build();
        return setmealOverViewVO;
    }

    /**
     * 查询菜品总览
     *
     * @return
     */
    @Override
    public DishOverViewVO overviewDishes() {
        Integer sold = dishMapper.countByStatus(StatusConstant.ENABLE);
        Integer discontinued = dishMapper.countByStatus(StatusConstant.DISABLE);
        DishOverViewVO dishOverViewVO = DishOverViewVO.builder().sold(sold).discontinued(discontinued).build();
        return dishOverViewVO;
    }

    /**
     * 查询订单管理数据
     *
     * @return
     */
    @Override
    public OrderOverViewVO overviewOrders() {
        Integer allOrders = orderMapper.count();
        Integer cancelledOrders = orderMapper.countByStatus(Orders.CANCELLED);
        Integer completedOrders = orderMapper.countByStatus(Orders.COMPLETED);
        Integer deliveredOrders = orderMapper.countByStatus(Orders.DELIVERY_IN_PROGRESS);
        Integer waitingOrders = orderMapper.countByStatus(Orders.TO_BE_CONFIRMED);
        OrderOverViewVO orderOverViewVO = OrderOverViewVO.builder().allOrders(allOrders).cancelledOrders(cancelledOrders).completedOrders(completedOrders).waitingOrders(waitingOrders).deliveredOrders(deliveredOrders).build();
        return orderOverViewVO;
    }

    /**
     * 查询今日运营数据
     * @return
     */
    @Override
    public BusinessDataVO businessData(LocalDate begin, LocalDate end) {
        Integer newUsers = userMapper.countUserByDate(end) - userMapper.countUserByDate(begin);
        Integer validOrderCount = orderMapper.countValidByDate(end);
        Double orderCompletionRate = 0.0;
        Double turnover = 0.0;
        Double unitPrice = 0.0;
        if(validOrderCount > 0){
            orderCompletionRate = validOrderCount * 1.0 / orderMapper.countByDate(end);
            turnover = orderMapper.sumByDate(end);
            unitPrice = (double) Math.round(turnover /  orderMapper.countByDate((end)));
        }
        BusinessDataVO businessDataVO = BusinessDataVO.builder().turnover(turnover).validOrderCount(validOrderCount).orderCompletionRate(orderCompletionRate).unitPrice(unitPrice).newUsers(newUsers).build();
        return businessDataVO;
    }
}
