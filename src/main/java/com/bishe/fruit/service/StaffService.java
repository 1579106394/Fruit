package com.bishe.fruit.service;

import com.bishe.fruit.pojo.Staff;
import com.bishe.fruit.utils.Page;

public interface StaffService {

    // 新增员工或者注册用户
    void addStaff(Staff staff);

    // 根据账号查询用户
    Staff getStaffByStaffAccount(Staff staff);

    // 根据手机号查询用户
    Staff getStaffByTelephone(Long staffTelephone);

    // 获取员工列表
    Page<Staff> getStaffList(Page<Staff> p);

    // 管理员新增员工
    void addStaffByAdmin(Staff staff);

    // 编辑员工
    void editStaff(Staff staff);

    // 根据id删除员工
    void deleteStaffById(String staffId);

    // 根据id获取员工
    Staff getStaffById(String staffId);

    // 切换管理员身份
    void editStaffRoleById(String staffId);

}
