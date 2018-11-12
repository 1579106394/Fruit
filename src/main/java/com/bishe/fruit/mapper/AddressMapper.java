package com.bishe.fruit.mapper;

import com.bishe.fruit.pojo.Address;
import com.bishe.fruit.utils.Page;

import java.util.List;

public interface AddressMapper {

    // 查询送货地址列表
    List<Address> getAddressList(Page<Address> p);

    // 查询送货地址总数
    Integer getAddressCount(Page<Address> p);

    // 新增送货地址
    void addAddress(Address address);

    // 编辑送货地址
    void editAddress(Address address);

    // 根据id删除送货地址
    void deleteAddressById(String addressId);

    // 根据id获取送货地址
    Address getAddressById(String addressId);
}
