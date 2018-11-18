package com.fruit.service;

import com.fruit.pojo.Address;
import com.fruit.pojo.Staff;
import com.fruit.utils.Page;

import java.util.List;

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

    // 获取全部的送货地址
    List<Address> getAllAddressList();

    // 为员工分配送货地址
    void addAddressForStaff(String staffId, String[] ids);

    // 根据员工获取被分配的外送地址
    List<Address> getAddressListByStaff(Staff staff);
}
