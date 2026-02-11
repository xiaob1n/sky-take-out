package com.sky.service;

public interface ShopService {
    /**
     * 查询营业状态
     */
    Integer getStatus();

    /**
     * 设置营业状态
     * @param status
     */
    void setStatus(Integer status);
}
