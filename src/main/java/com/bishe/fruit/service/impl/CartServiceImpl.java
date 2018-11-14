package com.bishe.fruit.service.impl;

import com.bishe.fruit.mapper.CartMapper;
import com.bishe.fruit.pojo.Cart;
import com.bishe.fruit.pojo.Fruit;
import com.bishe.fruit.pojo.Staff;
import com.bishe.fruit.service.CartService;
import com.bishe.fruit.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public void addCart(Fruit fruit) {
        // 先查询该用户有没有购物车，如果没有，就创建一个
        Cart cart = cartMapper.getCartByStaff(fruit.getStaff());
        if(cart == null) {
            // 没有购物车，创建一个
            cart = new Cart();
            // 补全属性
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCartCreatedTime(DateUtils.newDate());
            cart.setStaff(fruit.getStaff());
            cart.setCartFlag(1);
            cartMapper.creatCart(cart);
        }

        cart = cartMapper.getCartByStaff(fruit.getStaff());

        Map<String, Object> fruitMap = new HashMap<>();
        fruitMap.put("fruitCartId", UUID.randomUUID().toString());
        fruitMap.put("fruitId", fruit.getFruitId());
        fruitMap.put("cartId", cart.getCartId());
        fruitMap.put("fruitNum", 1);
        cartMapper.addCart(fruitMap);
    }

    @Override
    public Cart getCartList(Staff staff) {
        return cartMapper.getCartList(staff);
    }

    @Override
    public void deleteFromCart(String fruitId, String cartId) {
        cartMapper.deleteFromCart(fruitId, cartId);
    }
}
