package com.fruit.service.impl;


import com.fruit.mapper.*;
import com.fruit.pojo.*;
import com.fruit.service.OrderService;
import com.fruit.utils.*;
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

    @Autowired
    private SaleMapper saleMapper;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private JedisClient jedisClient;

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
            foMap.put("fruitNum", fruit.getFruitNum());
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

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "添加了订单";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);
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

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "查看了订单列表";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        return p;
    }

    @Override
    public void deleteOrderById(String orderId) {
        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "删除了ID为" + orderId + "的订单";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        orderMapper.deleteOrderById(orderId);
    }

    @Override
    public void placeOrderById(String orderId) {
        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "将ID为" + orderId + "的订单下单";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        orderMapper.placeOrderById(orderId);
    }

    @Override
    public Order getOrderById(String orderId) {
        return orderMapper.getOrderById(orderId);
    }

    @Override
    public void addCourier(Order order) {
        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "给订单" + order.getOrderId() + "分配了配送员，配送员是" + order.getCourier().getStaffName();
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        orderMapper.addCourier(order);
    }

    @Override
    public void deliver(String orderId) {
        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "将ID 为" + orderId + "的订单发货";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

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

        // 库存中减去对应的水果的数量
        List<Fruit> fruitList = order.getFruitList();
        for (Fruit fruit : fruitList) {
            Integer num = orderMapper.getFruitNumByFruitIdAndOrderId(fruit.getFruitId(), order.getOrderId());
            fruit.setFruitNum(num);
            Fruit f = fruitMapper.getFruitById(fruit.getFruitId());
            f.setFruitNum(f.getFruitNum() - fruit.getFruitNum());
            fruitMapper.editFruit(f);
            // 顺便在销售记录表中记录卖出的数量
            Sale sale = new Sale();
            sale.setSaleId(UUID.randomUUID().toString());
            sale.setFruit(fruit);
            sale.setSaleCreatedTime(DateUtils.newDate());
            sale.setSaleNum(fruit.getFruitNum());
            sale.setSaleFlag(1);

            saleMapper.addSale(sale);
        }

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "将ID 为" + orderId + "的订单收货";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

    }
}
