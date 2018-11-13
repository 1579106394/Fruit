package com.bishe.fruit.mapper;

import com.bishe.fruit.pojo.Cart;
import com.bishe.fruit.pojo.Staff;

import java.util.List;
import java.util.Map;

public interface CartMapper {

    // 根据用户id获取购物车
    Cart getCartByStaff(Staff staff);

    // 创建购物车
    void creatCart(Cart cart);

    // 将水果添加进购物车
    void addCart(Map<String,Object> fruitMap);

    // 查询购物车列表
    List<Cart> getCartList(Staff staff);
}