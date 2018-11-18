package com.fruit.controller;

import com.fruit.pojo.Address;
import com.fruit.pojo.Cart;
import com.fruit.pojo.Fruit;
import com.fruit.pojo.Staff;
import com.fruit.service.AddressService;
import com.fruit.service.CartService;
import com.fruit.service.FruitService;
import com.fruit.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private AddressService addressService;

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

    /**
     * 获取购物车中的水果
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("cartList.html")
    public String cartList(Model model, HttpSession session) {

        Staff staff = (Staff) session.getAttribute("staff");

        Cart cart = cartService.getCartList(staff);

        model.addAttribute("cart", cart);

        return "cart/cart-list";
    }

    /**
     * 根据id从购物车中移出水果
     * @param fruitId
     * @param cartId
     * @param ids
     * @return
     */
    @RequestMapping("deleteFromCart{cartId}/{fruitId}.html")
    public String deleteFromCart(@PathVariable String fruitId, @PathVariable String cartId, String[] ids) {
        if(StringUtils.isNotBlank(fruitId)) {
            cartService.deleteFromCart(fruitId, cartId);
        }else {
            for (String id : ids) {
                cartService.deleteFromCart(id, cartId);
            }
        }
        return "redirect:/api/cart/cartList.html";
    }

    /**
     * 跳转到提交订单页面
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("toAddOrder.html")
    public String toAddOrder(Model model, HttpSession session) {

        Staff staff = (Staff) session.getAttribute("staff");
        Cart cart = cartService.getCartList(staff);
        model.addAttribute("cart", cart);

        List<Address> addressList = addressService.getAllAddressList();
        model.addAttribute("addressList", addressList);

        return "cart/fruit-list";
    }

}
