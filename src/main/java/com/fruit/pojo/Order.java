package com.fruit.pojo;

import java.io.Serializable;
import java.util.*;

/**
 * CREATE TABLE `orders` (
 *   `order_id` varchar(36) NOT NULL,
 *   `order_created_time` varchar(36) NOT NULL,
 *   `order_staff` varchar(36) NOT NULL,
 *   `order_price` double(10,0) NOT NULL DEFAULT '0' COMMENT '订单总价',
 *   `order_add` varchar(36) NOT NULL COMMENT '订单收货地址',
 *   `order_flag` int(2) NOT NULL DEFAULT '1' COMMENT '1正常2删除3已收货',
 *   PRIMARY KEY (`order_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class Order implements Serializable {

    private String orderId;
    private String orderCreatedTime;
    private Staff staff;
    private Staff courier;
    private Double orderPrice;
    private Address address;
    private Integer orderFlag;
    private List<Fruit> fruitList = new ArrayList<Fruit>();

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderCreatedTime() {
        return orderCreatedTime;
    }

    public void setOrderCreatedTime(String orderCreatedTime) {
        this.orderCreatedTime = orderCreatedTime;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Staff getCourier() {
        return courier;
    }

    public void setCourier(Staff courier) {
        this.courier = courier;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(Integer orderFlag) {
        this.orderFlag = orderFlag;
    }

    public List<Fruit> getFruitList() {
        return fruitList;
    }

    public void setFruitList(List<Fruit> fruitList) {
        this.fruitList = fruitList;
    }
}
