package com.bishe.fruit.service.impl;


import com.bishe.fruit.mapper.*;
import com.bishe.fruit.pojo.*;
import com.bishe.fruit.service.OrderService;
import com.bishe.fruit.utils.DateUtils;
import com.bishe.fruit.utils.HistoryUtils;
import com.bishe.fruit.utils.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private FruitMapper fruitMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public void addOrder(Order order) {
        // 补全属性
        order.setOrderId(UUID.randomUUID().toString());
        order.setOrderCreatedTime(DateUtils.newDate());
        order.setOrderFlag(1);
        Double price = 0.0;
        //还剩price
        List<Fruit> fruitList = order.getFruitList();
        for (Fruit fruit : fruitList) {
            Fruit f = fruitMapper.getFruitById(fruit.getFruitId());
            Double fruitPrice = f.getFruitPrice();
            price += fruitPrice;

            Map<String, Object> foMap = new HashMap<>();
            foMap.put("fruitOrderId", UUID.randomUUID().toString());
            foMap.put("fruitId", fruit.getFruitId());
            foMap.put("orderId", order.getOrderId());
            // 在fruit_order表中生成数据
            orderMapper.addFruitInOrder(foMap);
        }
        order.setOrderPrice(price);
        // 数据插入orders表中
        orderMapper.addOrderNotCourier(order);

        // 订单生成完毕，删除购物车中的数据
        Staff staff = order.getStaff();
        Cart cart = cartMapper.getCartByStaff(staff);
        for (Fruit fruit : fruitList) {
            cartMapper.deleteFromCart(fruit.getFruitId(), cart.getCartId());
        }
    }

    @Override
    public Page<Order> getOrderList(Page<Order> p) {
        if (p.getCurrentPage() == null) {
            p.setCurrentPage(1);
        }

        if (p.getCurrentCount() == null) {
            p.setCurrentCount(10);
        }

        Integer currentPage = p.getCurrentPage();
        Integer currentCount = p.getCurrentCount();

        Integer index = (currentPage - 1) * currentCount;
        p.setIndex(index);

        List<Order> orderList = orderMapper.getOrderList(p);
        // 获取到的列表，派送员有点问题，需要重新设置派送员
        for (Order order : orderList) {
            String courierId = orderMapper.getCourierByOrderId(order.getOrderId());
            if (StringUtils.isBlank(courierId)) {
                order.setCourier(null);
            } else {
                Staff courier = staffMapper.getStaffById(courierId);
                order.setCourier(courier);
            }
        }

        p.setList(orderList);

        Integer totalCount = orderMapper.getOrderCount(p);
        p.setTotalCount(totalCount);

        Integer totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
        p.setTotalPage(totalPage);

        return p;
    }

    @Override
    public void deleteOrderById(String orderId) {
        orderMapper.deleteOrderById(orderId);
    }

    @Override
    public void placeOrderById(String orderId) {
        orderMapper.placeOrderById(orderId);
    }

    @Override
    public Order getOrderById(String orderId) {
        return orderMapper.getOrderById(orderId);
    }

    @Override
    public void addCourier(Order order) {
        orderMapper.addCourier(order);
    }

    @Override
    public void deliver(String orderId) {
        orderMapper.deliver(orderId);
    }

    @Override
    public void collect(String orderId) {
        orderMapper.collect(orderId);
        // 确定收货后，需要生成财务记录，并且在公司财务上加上订单金额
        Order order = orderMapper.getOrderById(orderId);
        Double price = order.getOrderPrice();
        historyMapper.pay(0 - price); // 支出负金额就是收入
        Staff staff = order.getStaff();
        History history = HistoryUtils.newHistory(price, "用户" + staff.getStaffName() + "订单付款，收入" + price + "元", 2);
        historyMapper.addHistory(history);
    }
}
