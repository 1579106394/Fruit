package com.bishe.fruit.mapper;

import com.bishe.fruit.pojo.Staff;

public interface StaffMapper {

    // 根据账号获取用户
    Staff getStaffByStaffAccount(Staff staff);

    // 根据手机号获取用户
    Staff getStaffByTelephone(Long staffTelephone);

    // 新增用户
    void addStaff(Staff staff);
}
