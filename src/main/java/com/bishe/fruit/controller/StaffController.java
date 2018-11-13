package com.bishe.fruit.controller;

import com.bishe.fruit.pojo.Address;
import com.bishe.fruit.pojo.Staff;
import com.bishe.fruit.service.AddressService;
import com.bishe.fruit.service.StaffService;
import com.bishe.fruit.utils.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/staff/")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private AddressService addressService;

    /**
     * 员工列表
     */
    @RequestMapping("staffList.html")
    public String staffList(Model model, Page<Staff> p) {

        p.getParams().put("staffRole", 1);

        Page<Staff> page = staffService.getStaffList(p);

        model.addAttribute("page", page);

        return "staff/staff-list";
    }

    /**
     * 用户列表
     */
    @RequestMapping("userList.html")
    public String userList(Model model, Page<Staff> p) {

        p.getParams().put("staffRole", 2);

        Page<Staff> page = staffService.getStaffList(p);

        model.addAttribute("page", page);

        return "user/user-list";
    }

    /**
     * 添加员工
     */
    @RequestMapping("addStaff.html")
    public String addStaff(Staff staff) {

        staffService.addStaffByAdmin(staff);

        return "redirect:/api/staff/staffList.html";
    }

    /**
     * 编辑员工
     */
    @RequestMapping("editStaff.html")
    public String editStaff(Staff staff) {

        staffService.editStaff(staff);

        return "redirect:/api/staff/staffList.html";
    }

    /**
     * 根据id删除员工
     */
    @RequestMapping("deleteStaff{staffId}.html")
    public String deleteStaff(@PathVariable String staffId, String[] ids) {

        if (StringUtils.isNotBlank(staffId)) {
            staffService.deleteStaffById(staffId);
        } else {
            for (String id : ids) {
                staffService.deleteStaffById(id);
            }
        }


        return "redirect:/api/staff/staffList.html";
    }

    /**
     * 根据id删除用户
     */
    @RequestMapping("deleteUser{staffId}.html")
    public String deleteUser(@PathVariable String staffId, String[] ids) {

        if (StringUtils.isNotBlank(staffId)) {
            staffService.deleteStaffById(staffId);
        } else {
            for (String id : ids) {
                staffService.deleteStaffById(id);
            }
        }


        return "redirect:/api/staff/userList.html";
    }

    /**
     * 跳转到编辑员工
     */
    @RequestMapping("toEdit{staffId}.html")
    public String toEdit(@PathVariable String staffId, Model model) {
        Staff staff = staffService.getStaffById(staffId);
        model.addAttribute("staff", staff);
        return "staff/staff-edit";
    }

    @RequestMapping("editRole{staffId}.html")
    public String editRole(@PathVariable String staffId) {

        staffService.editStaffRoleById(staffId);

        return "redirect:/api/staff/staffList.html";
    }

    /**
     * 跳转到分配外送地址页面
     */
    @RequestMapping("toAddAddress{staffId}.html")
    public String toAddAddress(@PathVariable String staffId, Model model) {

        Staff staff = new Staff();
        staff.setStaffId(staffId);
        model.addAttribute("staff", staff);

        List<Address> addressList = addressService.getAllAddressList();

        model.addAttribute("addressList", addressList);

        return "staff/staff-add-address";
    }


    /**
     * 分配收货地址
     */
    @RequestMapping("addAddress.html")
    public String addAddress(String staffId, String[] ids) {

        addressService.addAddressForStaff(staffId, ids);

        return "redirect:/api/staff/staffList.html";
    }

}
