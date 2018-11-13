package com.bishe.fruit.service;

import com.bishe.fruit.pojo.Cart;
import com.bishe.fruit.pojo.Fruit;
import com.bishe.fruit.pojo.Staff;

import java.util.List;

public interface CartService {

    // 把水果加入购物车
    void addCart(Fruit fruit);

    List<Cart> getCartList(Staff staff);
}
