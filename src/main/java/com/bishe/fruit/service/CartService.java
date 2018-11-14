package com.bishe.fruit.service;

import com.bishe.fruit.pojo.Cart;
import com.bishe.fruit.pojo.Fruit;
import com.bishe.fruit.pojo.Staff;

public interface CartService {

    // 把水果加入购物车
    void addCart(Fruit fruit);

    // 获取购物车
    Cart getCartList(Staff staff);

    // 从购物车中移出水果
    void deleteFromCart(String fruitId, String cartId);
}
