package com.fruit.service.impl;

import com.fruit.mapper.CartMapper;
import com.fruit.mapper.FruitMapper;
import com.fruit.mapper.LogMapper;
import com.fruit.pojo.Cart;
import com.fruit.pojo.Fruit;
import com.fruit.pojo.Log;
import com.fruit.pojo.Staff;
import com.fruit.service.CartService;
import com.fruit.utils.DateUtils;
import com.fruit.utils.JedisClient;
import com.fruit.utils.JsonUtils;
import com.fruit.utils.LogUtils;
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

    @Autowired
    private FruitMapper fruitMapper;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public void addCart(Fruit fruit) {
        // 先查询该用户有没有购物车，如果没有，就创建一个
        Cart cart = cartMapper.getCartByStaff(fruit.getStaff());
        if (cart == null) {
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

        Fruit f = fruitMapper.getFruitById(fruit.getFruitId());

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "将" + f.getFruitName() + "添加进了购物车";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);
    }

    @Override
    public Cart getCartList(Staff staff) {
        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "查看了购物车";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        return cartMapper.getCartList(staff);
    }

    @Override
    public void deleteFromCart(String fruitId, String cartId) {
        cartMapper.deleteFromCart(fruitId, cartId);

        Fruit fruit = fruitMapper.getFruitById(fruitId);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "将" + fruit.getFruitName() + "移出了购物车";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);
    }
}
