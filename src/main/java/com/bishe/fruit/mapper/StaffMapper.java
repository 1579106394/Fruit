package com.bishe.fruit.mapper;

import com.bishe.fruit.pojo.Address;
import com.bishe.fruit.pojo.Staff;
import com.bishe.fruit.utils.Page;

import java.util.List;

public interface StaffMapper {

    // 根据账号获取用户
    Staff getStaffByStaffAccount(Staff staff);

    // 根据手机号获取用户
    Staff getStaffByTelephone(Long staffTelephone);

    // 新增用户
    void addStaff(Staff staff);

    // 获取员工列表
    List<Staff> getStaffList(Page<Staff> p);

    // 获取员工总数
    Integer getStaffCount(Page<Staff> p);

    // 编辑员工
    void editStaff(Staff staff);

    // 根据id删除员工
    void deleteStaffById(String staffId);

    // 根据id获取员工
    Staff getStaffById(String staffId);

    // 切换员工身份
    void editStaffRole(Staff staff);

    // 获取所有员工(包括管理员)
    List<Staff> getAllStaff();

    // 根据送货地址获取派送员
    List<Staff> getStaffListByAddress(Address address);
}
