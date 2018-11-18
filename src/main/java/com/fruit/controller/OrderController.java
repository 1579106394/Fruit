package com.fruit.controller;

import com.fruit.pojo.Address;
import com.fruit.pojo.Order;
import com.fruit.pojo.Staff;
import com.fruit.service.OrderService;
import com.fruit.service.StaffService;
import com.fruit.utils.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("api/order/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StaffService staffService;

    /**
     * 添加订单
     * @param order
     * @param session
     * @return
     */
    @RequestMapping("addOrder.html")
    public String addOrder(Order order, HttpSession session) {

        Staff staff = (Staff) session.getAttribute("staff");
        order.setStaff(staff);
        orderService.addOrder(order);

        return "redirect:/api/order/orderList.html";
    }

    /**
     * 查询订单列表
     * @param model
     * @param p
     * @return
     */
    @RequestMapping("orderList.html")
    public String orderList(Model model, Page<Order> p, HttpSession session) {
        Staff staff = (Staff) session.getAttribute("staff");
        if(staff.getStaffRole() == 2) {
            p.getParams().put("staff", staff);
        }
        Page<Order> page = orderService.getOrderList(p);
        model.addAttribute("page", page);
        return "order/order-list";
    }

    /**
     * 根据id删除订单
     * @param orderId
     * @param ids
     * @return
     */
    @RequestMapping("deleteOrder{orderId}.html")
    public String deleteOrder(@PathVariable String orderId, String[] ids) {
        if(StringUtils.isNotBlank(orderId)) {
            orderService.deleteOrderById(orderId);
        }else {
            for (String id : ids) {
                orderService.deleteOrderById(id);
            }
        }
        return "redirect:/api/order/orderList.html";
    }

    /**
     * 下单
     * @param orderId
     * @return
     */
    @RequestMapping("placeOrder{orderId}.html")
    public String placeOrder(@PathVariable String orderId) {
        orderService.placeOrderById(orderId);
        return "redirect:/api/order/orderList.html";
    }

    /**
     * 发货
     * @param orderId
     * @return
     */
    @RequestMapping("deliver{orderId}.html")
    public String deliver(@PathVariable String orderId) {
        orderService.deliver(orderId);
        return "redirect:/api/order/orderList.html";
    }

    /**
     * 收货
     * @param orderId
     * @return
     */
    @RequestMapping("collect{orderId}.html")
    public String collect(@PathVariable String orderId) {
        orderService.collect(orderId);
        return "redirect:/api/order/orderList.html";
    }

    /**
     * 跳转到添加派送员页面
     * @param orderId
     * @param model
     * @return
     */
    @RequestMapping("toAssignStaff{orderId}.html")
    public String toAssignStaff(@PathVariable String orderId, Model model) {
        Order order = orderService.getOrderById(orderId);
        Address address = order.getAddress();
        // 根据送货地址查询派送员，放到model中
        List<Staff> staffList = staffService.getStaffListByAddress(address);
        model.addAttribute("staffList", staffList);
        model.addAttribute("order", order);

        return "order/order-add-staff";
    }

    /**
     * 分配外送员
     * @param order
     * @return
     */
    @RequestMapping("assignStaff.html")
    public String assignStaff(Order order) {

        orderService.addCourier(order);

        return "redirect:/api/order/orderList.html";
    }

}
