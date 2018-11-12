package com.bishe.fruit.service;

import com.bishe.fruit.pojo.Address;
import com.bishe.fruit.utils.Page;

public interface AddressService {

    // 获取送货地址列表
    Page<Address> getAddressList(Page<Address> p);

    // 添加收货地址
    void addAddress(Address address);

    // 编辑收货地址
    void editAddress(Address address);

    // 根据id删除收货地址
    void deleteAddressById(String addressId);

    // 根据id获取收货地址
    Address getAddressById(String addressId);
}
