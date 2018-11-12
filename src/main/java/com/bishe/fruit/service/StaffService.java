package com.bishe.fruit.service;

import com.bishe.fruit.pojo.Staff;

public interface StaffService {

    // 新增员工或者注册用户
    void addStaff(Staff staff);

    // 根据账号查询用户
    Staff getStaffByStaffAccount(Staff staff);

    // 根据手机号查询用户
    Staff getStaffByTelephone(Long staffTelephone);
}
