package com.fruit.mapper;

import com.fruit.pojo.Address;
import com.fruit.pojo.Staff;
import com.fruit.utils.Page;

import java.util.List;
import java.util.Map;

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

    // 获取所有的送货地址
    List<Address> getAllAddressList();

    // 分配送货地址
    void addStaffAddress(Map<String,Object> map);

    // 根据员工获取外送地址
    List<Address> getAddressListByStaff(Staff staff);

    // 根据员工id和地址id获取对应的分配地址是否存在
    Address getAddressByStaffAndAddress(String staffId, String id);
}
