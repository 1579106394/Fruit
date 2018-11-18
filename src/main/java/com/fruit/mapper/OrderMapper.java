package com.fruit.mapper;

import com.fruit.pojo.Order;
import com.fruit.utils.Page;

import java.util.List;
import java.util.Map;

public interface OrderMapper {

    // 提交订单（没有送货员）
    void addOrderNotCourier(Order order);

    // 在fruit_order表中生成对应的数据
    void addFruitInOrder(Map<String,Object> foMap);

    // 获取订单列表
    List<Order> getOrderList(Page<Order> p);

    // 获取订单数量
    Integer getOrderCount(Page<Order> p);

    // 根据id删除订单
    void deleteOrderById(String orderId);

    // 根据id下单
    void placeOrderById(String orderId);

    // 根据id获取订单
    Order getOrderById(String orderId);

    // 根据订单id获取派送员
    String getCourierByOrderId(String orderId);

    // 添加派送员
    void addCourier(Order order);

    // 收货
    void collect(String orderId);

    // 发货
    void deliver(String orderId);

    // 根据fruitId和orderId查水果数量
    Integer getFruitNumByFruitIdAndOrderId(String fruitId, String orderId);
}
