package com.fruit.controller;

import com.fruit.pojo.Staff;
import com.fruit.service.StaffService;
import com.fruit.utils.JedisClient;
import com.fruit.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private JedisClient jedisClient;

    /**
     * 注册
     *
     * @param model
     * @param staff
     * @return
     */
    @RequestMapping("/register.html")
    public String register(Model model, Staff staff) {

        try {
            // 根据账号查询用户是否存在，如果存在，提示用户已存在
            Staff s = staffService.getStaffByStaffAccount(staff);
            if (s != null) {
                model.addAttribute("error", "该用户已存在！");
                return "register";
            }

            // 根据手机号查询用户，如果手机号已存在，提示
            /*Long staffTelephone = staff.getStaffTelephone();
            if (!staffTelephone.toString().matches("^[1]([3|5|8][0-9]{1})[0-9]{8}$")) {
                // 手机号格式不匹配
                model.addAttribute("error", "手机号格式不正确！");
                return "register";
            }
            s = staffService.getStaffByTelephone(staffTelephone);
            if (s != null) {
                model.addAttribute("error", "该手机号已存在！");
                return "register";
            }*/

            //用户不存在
            staffService.addStaff(staff);

            return "login";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "请填写所有项");
            return "register";
        }
    }

    /**
     * 登录
     * @param model
     * @param staff
     * @param session
     * @return
     */
    @RequestMapping("/login.html")
    public String login(Model model, Staff staff, HttpSession session) {

        Staff s = staffService.getStaffByStaffAccount(staff);

        if(s == null) {
            model.addAttribute("error", "用户名或密码错误！");
            return "login";
        }else {
            // 用户存在
            if(!s.getStaffPassword().equals(staff.getStaffPassword())) {
                // 密码错误
                model.addAttribute("error", "用户名或密码错误！");
                return "login";
            }
        }

        session.setAttribute("staff", s);
        jedisClient.set("staff", JsonUtils.objectToJson(s));


        return "redirect:/index.html";
    }

    /**
     * 注销登录
     * @param session
     * @return
     */
    @RequestMapping("/logout.html")
    public String logout(HttpSession session) {
        session.removeAttribute("staff");
        return "login";
    }


}
