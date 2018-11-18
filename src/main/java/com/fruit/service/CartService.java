package com.fruit.service;

import com.fruit.pojo.Cart;
import com.fruit.pojo.Fruit;
import com.fruit.pojo.Staff;

public interface CartService {

    // 把水果加入购物车
    void addCart(Fruit fruit);

    // 获取购物车
    Cart getCartList(Staff staff);

    // 从购物车中移出水果
    void deleteFromCart(String fruitId, String cartId);
}
