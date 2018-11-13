package com.bishe.fruit.service.impl;

import com.bishe.fruit.mapper.SalaryMapper;
import com.bishe.fruit.mapper.StaffMapper;
import com.bishe.fruit.pojo.Salary;
import com.bishe.fruit.pojo.Staff;
import com.bishe.fruit.service.StaffService;
import com.bishe.fruit.utils.DateUtils;
import com.bishe.fruit.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private SalaryMapper salaryMapper;

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

    @Override
    public Page<Staff> getStaffList(Page<Staff> p) {
        if(p.getCurrentPage() == null) {
            p.setCurrentPage(1);
        }

        if (p.getCurrentCount() == null) {
            p.setCurrentCount(10);
        }

        Integer currentPage = p.getCurrentPage();
        Integer currentCount = p.getCurrentCount();

        Integer index = (currentPage - 1) * currentCount;
        p.setIndex(index);

        List<Staff> staffList = staffMapper.getStaffList(p);
        p.setList(staffList);

        Integer totalCount = staffMapper.getStaffCount(p);
        p.setTotalCount(totalCount);

        Integer totalPage = (int)Math.ceil(totalCount * 1.0 / currentCount);
        p.setTotalPage(totalPage);

        return p;
    }

    @Override
    public void addStaffByAdmin(Staff staff) {
        // 补全属性
        staff.setStaffId(UUID.randomUUID().toString());
        staff.setStaffRole(1);
        staff.setStaffFlag(1);
        staffMapper.addStaff(staff);

        // 存入工资表
        Salary salary = staff.getSalary();
        // 补全属性
        salary.setSalaryId(UUID.randomUUID().toString());
        salary.setSalaryTime(DateUtils.newDate());
        salary.setStaff(staff);
        salary.setSalaryFlag(1);
        salaryMapper.addSalary(salary);

    }

    @Override
    public void editStaff(Staff staff) {
        staffMapper.editStaff(staff);
        // 用户信息编辑完毕，编辑工资
        Salary salary = staff.getSalary();
        salary.setStaff(staff);
        salaryMapper.editSalary(salary);
    }

    @Override
    public void deleteStaffById(String staffId) {
        staffMapper.deleteStaffById(staffId);
    }

    @Override
    public Staff getStaffById(String staffId) {
        return staffMapper.getStaffById(staffId);
    }

    @Override
    public void editStaffRoleById(String staffId) {
        Staff staff = staffMapper.getStaffById(staffId);
        staffMapper.editStaffRole(staff);
    }


}
