package com.fruit.controller;

import com.fruit.mapper.AddressMapper;
import com.fruit.mapper.FruitMapper;
import com.fruit.mapper.OrderMapper;
import com.fruit.mapper.StaffMapper;
import com.fruit.pojo.Address;
import com.fruit.pojo.Fruit;
import com.fruit.pojo.Order;
import com.fruit.pojo.Staff;
import com.fruit.service.HistoryService;
import com.fruit.service.OrderService;
import com.fruit.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private FruitMapper fruitMapper;

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 跳转到首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/index.html")
    public String index(Model model, HttpSession session) {
        Staff staff = (Staff) session.getAttribute("staff");

        // 查询是否有新下单的订单、需要派送的订单、公司账务
        if (staff.getStaffRole() != 2) {

            Page p = new Page();
            p.getParams().put("flag", 3);
            Page<Order> newsPage = orderService.getOrderList(p); // 新订单
            if (newsPage.getList() != null && newsPage.getList().size() != 0) {
                model.addAttribute("orderFlag1", 1);
            }

            // 需要派送的订单(已下单，且分配了派送员)
            p.getParams().put("flag", 4);
            p.getParams().put("courier", staff);
            newsPage = orderService.getOrderList(p);
            if (newsPage.getList() != null && newsPage.getList().size() != 0) {
                model.addAttribute("OrderFlag2", 1);
            }

        }
        // 公司账务
        if (staff.getStaffRole() != 2) {

            Double adminPrice = historyService.getAdminPrice();
            model.addAttribute("adminPrice", adminPrice);

            Map<String, Object> dataMap = new HashMap<>();
            // 查询水果数
            Integer fruitCount = fruitMapper.getFruitCount(new Page<Fruit>());
            dataMap.put("fruitCount", fruitCount);
            // 查询员工数
            Page staffPage = new Page();
            staffPage.getParams().put("staffRole", 1);
            Integer staffCount = staffMapper.getStaffCount(staffPage);
            dataMap.put("staffCount", staffCount);
            // 查询普通用户数
            staffPage.getParams().put("staffRole", 2);
            Integer userCount = staffMapper.getStaffCount(staffPage);
            dataMap.put("userCount", userCount);
            // 查询管理员数
            staffPage.getParams().put("staffRole", 3);
            Integer adminCount = staffMapper.getStaffCount(staffPage);
            dataMap.put("adminCount", adminCount);
            // 查询外送地址数
            Integer addressCount = addressMapper.getAddressCount(new Page<Address>());
            dataMap.put("addressCount", addressCount);
            // 查询订单数
            Integer orderCount = orderMapper.getOrderCount(new Page<Order>());
            dataMap.put("orderCount", orderCount);

            model.addAttribute("dataMap", dataMap);

        }
        return "index";
    }

}
