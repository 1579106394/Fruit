package com.fruit.pojo;

import java.io.Serializable;

/**
 * CREATE TABLE `address` (
 *   `address_id` varchar(36) NOT NULL COMMENT '外送地址id',
 *   `address_name` varchar(36) NOT NULL,
 *   `address_created_time` varchar(36) NOT NULL,
 *   `address_flag` int(2) NOT NULL DEFAULT '1' COMMENT '1正常2删除',
 *   PRIMARY KEY (`address_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class Address implements Serializable {

    private String addressId;
    private String addressName;
    private String addressCreatedTime;
    private Integer addressFlag;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressCreatedTime() {
        return addressCreatedTime;
    }

    public void setAddressCreatedTime(String addressCreatedTime) {
        this.addressCreatedTime = addressCreatedTime;
    }

    public Integer getAddressFlag() {
        return addressFlag;
    }

    public void setAddressFlag(Integer addressFlag) {
        this.addressFlag = addressFlag;
    }
}
