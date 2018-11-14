package com.bishe.fruit.service;

import com.bishe.fruit.pojo.Order;
import com.bishe.fruit.utils.Page;

public interface OrderService {

    // 新订单
    void addOrder(Order order);

    // 获取订单列表
    Page<Order> getOrderList(Page<Order> p);

    // 根据id删除订单
    void deleteOrderById(String orderId);

    // 根据id下单
    void placeOrderById(String orderId);

    // 根据id获取订单
    Order getOrderById(String orderId);

    // 分配派送员
    void addCourier(Order order);

    // 发货
    void deliver(String orderId);

    // 收货
    void collect(String orderId);
}
