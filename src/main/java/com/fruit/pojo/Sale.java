package com.fruit.pojo;

import java.io.Serializable;

/**
 * CREATE TABLE `sale` (
 *   `sale_id` varchar(36) NOT NULL,
 *   `sale_fruit` varchar(36) NOT NULL,
 *   `sale_created_time` varchar(36) NOT NULL,
 *   `sale_num` int(5) NOT NULL DEFAULT '0',
 *   `sale_flag` int(2) NOT NULL DEFAULT '1' COMMENT '1正常2删除',
 *   PRIMARY KEY (`sale_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class Sale implements Serializable {

    private String saleId;
    private Fruit fruit;
    private String saleCreatedTime;
    private Integer saleNum;
    private Integer saleFlag;

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public Fruit getFruit() {
        return fruit;
    }

    public void setFruit(Fruit fruit) {
        this.fruit = fruit;
    }

    public String getSaleCreatedTime() {
        return saleCreatedTime;
    }

    public void setSaleCreatedTime(String saleCreatedTime) {
        this.saleCreatedTime = saleCreatedTime;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Integer getSaleFlag() {
        return saleFlag;
    }

    public void setSaleFlag(Integer saleFlag) {
        this.saleFlag = saleFlag;
    }
}
