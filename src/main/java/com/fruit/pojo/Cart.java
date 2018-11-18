package com.fruit.pojo;

import java.io.Serializable;
import java.util.*;

/**
 * CREATE TABLE `cart` (
 *   `cart_id` varchar(36) NOT NULL,
 *   `cart_staff` varchar(36) NOT NULL COMMENT '购物车用户',
 *   `cart_created_time` varchar(36) NOT NULL COMMENT '购物车创建时间',
 *   `cart_flag` int(2) NOT NULL DEFAULT '1' COMMENT '1正常2删除',
 *   PRIMARY KEY (`cart_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class Cart implements Serializable {

    private String cartId;
    private Staff staff;
    private String cartCreatedTime;
    private Integer cartFlag;
    private List<Fruit> fruitList = new ArrayList<>();

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getCartCreatedTime() {
        return cartCreatedTime;
    }

    public void setCartCreatedTime(String cartCreatedTime) {
        this.cartCreatedTime = cartCreatedTime;
    }

    public Integer getCartFlag() {
        return cartFlag;
    }

    public void setCartFlag(Integer cartFlag) {
        this.cartFlag = cartFlag;
    }

    public List<Fruit> getFruitList() {
        return fruitList;
    }

    public void setFruitList(List<Fruit> fruitList) {
        this.fruitList = fruitList;
    }
}
