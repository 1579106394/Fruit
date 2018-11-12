package com.bishe.fruit.pojo;

import java.io.Serializable;

/**
 * CREATE TABLE `fruit` (
 *   `fruit_id` varchar(36) NOT NULL,
 *   `fruit_name` varchar(36) NOT NULL,
 *   `fruit_created_time` varchar(36) NOT NULL COMMENT '上架时间',
 *   `fruit_created_staff` varchar(36) NOT NULL COMMENT '上架员工',
 *   `fruit_price` double(10,0) NOT NULL DEFAULT '0' COMMENT '水果单价',
 *   `fruit_num` int(5) NOT NULL DEFAULT '0' COMMENT '库存',
 *   `fruit_flag` int(2) NOT NULL DEFAULT '1' COMMENT '1正常2删除',
 *   PRIMARY KEY (`fruit_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class Fruit implements Serializable {

    private String fruitId;
    private String fruitName;
    private String fruitCreatedTime;
    private Staff staff;
    private Double fruitPrice;
    private Integer fruitNum;
    private Integer fruitFlag;

    public String getFruitId() {
        return fruitId;
    }

    public void setFruitId(String fruitId) {
        this.fruitId = fruitId;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public String getFruitCreatedTime() {
        return fruitCreatedTime;
    }

    public void setFruitCreatedTime(String fruitCreatedTime) {
        this.fruitCreatedTime = fruitCreatedTime;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Double getFruitPrice() {
        return fruitPrice;
    }

    public void setFruitPrice(Double fruitPrice) {
        this.fruitPrice = fruitPrice;
    }

    public Integer getFruitNum() {
        return fruitNum;
    }

    public void setFruitNum(Integer fruitNum) {
        this.fruitNum = fruitNum;
    }

    public Integer getFruitFlag() {
        return fruitFlag;
    }

    public void setFruitFlag(Integer fruitFlag) {
        this.fruitFlag = fruitFlag;
    }
}
