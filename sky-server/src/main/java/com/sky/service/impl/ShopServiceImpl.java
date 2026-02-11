package com.sky.service.impl;

import com.sky.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询营业状态
     */
    @Override
    public Integer getStatus() {
        return (Integer)redisTemplate.opsForValue().get("SHOP_STATUS");
    }

    /**
     * 设置营业状态
     * @param status
     */
    @Override
    public void setStatus(Integer status) {
        redisTemplate.opsForValue().set("SHOP_STATUS", status);
    }
}
