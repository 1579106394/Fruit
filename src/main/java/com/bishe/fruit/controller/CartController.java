package com.bishe.fruit.controller;

import com.bishe.fruit.pojo.Cart;
import com.bishe.fruit.pojo.Fruit;
import com.bishe.fruit.pojo.Staff;
import com.bishe.fruit.service.CartService;
import com.bishe.fruit.service.FruitService;
import com.bishe.fruit.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("api/cart/")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private FruitService fruitService;

    /**
     * ajax加入购物车
     * @param fruit
     * @param session
     * @return
     */
    @RequestMapping("addCart.action")
    @ResponseBody
    public Result addCart(@RequestBody Fruit fruit, HttpSession session) {

        Staff staff = (Staff) session.getAttribute("staff");
        fruit.setStaff(staff);

        Fruit fruit1 = fruitService.getFruitFromCartByFruitId(fruit);
        if(fruit1 == null) {
            cartService.addCart(fruit);
            return Result.ok();
        }else {

            return Result.build(400, "购物车已存在该商品，请勿重复添加");
        }

    }

    @RequestMapping("cartList.html")
    public String cartList(Model model, HttpSession session) {

        Staff staff = (Staff) session.getAttribute("staff");

        List<Cart> cartList = cartService.getCartList(staff);

        return "cart/cart-list";
    }


}
