package com.bishe.fruit.service.impl;

import com.bishe.fruit.mapper.StaffMapper;
import com.bishe.fruit.pojo.Staff;
import com.bishe.fruit.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;

    @Override
    public void addStaff(Staff staff) {
        // 补全属性
        staff.setStaffId(UUID.randomUUID().toString());
        if(staff.getStaffRole() == null) {
            staff.setStaffRole(2);
        }
        staff.setStaffFlag(1);
        staffMapper.addStaff(staff);
    }

    @Override
    public Staff getStaffByStaffAccount(Staff staff) {
        return staffMapper.getStaffByStaffAccount(staff);
    }

    @Override
    public Staff getStaffByTelephone(Long staffTelephone) {
        return staffMapper.getStaffByTelephone(staffTelephone);
    }

}
