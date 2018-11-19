package com.fruit.service.impl;

import com.fruit.mapper.AddressMapper;
import com.fruit.mapper.LogMapper;
import com.fruit.mapper.SalaryMapper;
import com.fruit.mapper.StaffMapper;
import com.fruit.pojo.Address;
import com.fruit.pojo.Log;
import com.fruit.pojo.Salary;
import com.fruit.pojo.Staff;
import com.fruit.service.StaffService;
import com.fruit.utils.*;
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

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public void addStaff(Staff staff) {
        // 补全属性
        staff.setStaffId(UUID.randomUUID().toString());
        if(staff.getStaffRole() == null) {
            staff.setStaffRole(2);
        }
        staff.setStaffFlag(1);
        staffMapper.addStaff(staff);

        String time = DateUtils.newDate();
        String article = staff.getStaffName() + "在" + time + "注册为用户";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

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

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "查看了用户列表";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

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

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "添加了用户" + staff.getStaffName();
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

    }

    @Override
    public void editStaff(Staff staff) {

        Staff s = staffMapper.getStaffById(staff.getStaffId());


        staffMapper.editStaff(staff);
        // 用户信息编辑完毕，编辑工资
        Salary salary = staff.getSalary();
        salary.setStaff(staff);
        salaryMapper.editSalary(salary);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "修改了" + s.getStaffName() + "的信息，" +
                "账号从" + s.getStaffAccount() + "修改为" + staff.getStaffAccount() +
                "，密码从" + s.getStaffPassword() + "修改为" + staff.getStaffPassword() +
                "，姓名从" + s.getStaffName() + "修改为" + staff.getStaffName() +
                "，生日从" + s.getStaffBirth() + "修改为" + staff.getStaffBirth() +
                "，年龄从" + s.getStaffAge() + "修改为" + staff.getStaffAge() +
                "，手机号从" +s.getStaffTelephone() + "修改为" + staff.getStaffTelephone();
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);


    }

    @Override
    public void deleteStaffById(String staffId) {

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "删除了ID为" + staffId + "的用户";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

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
        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        Staff s = staffMapper.getStaffById(staffId);
        if(s.getStaffRole() == 1) {
            String time = DateUtils.newDate();
            String article = staffName + "在" + time + "将员工" + s.getStaffName() + "改回普通用户";
            Log log = LogUtils.newLog(time, article);
            logMapper.addLog(log);
        }else {
            String time = DateUtils.newDate();
            String article = staffName + "在" + time + "将员工" + s.getStaffName() + "设为管理员";
            Log log = LogUtils.newLog(time, article);
            logMapper.addLog(log);
        }

    }

    @Override
    public List<Staff> getStaffListByAddress(Address address) {

        Address a = addressMapper.getAddressById(address.getAddressId());
        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "查看了" + a.getAddressName() + "的派送员";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        return staffMapper.getStaffListByAddress(address);
    }


}
