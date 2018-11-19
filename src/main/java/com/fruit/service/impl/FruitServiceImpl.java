package com.fruit.service.impl;

import com.fruit.mapper.FruitMapper;
import com.fruit.mapper.HistoryMapper;
import com.fruit.mapper.LogMapper;
import com.fruit.pojo.Fruit;
import com.fruit.pojo.History;
import com.fruit.pojo.Log;
import com.fruit.pojo.Staff;
import com.fruit.service.FruitService;
import com.fruit.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class FruitServiceImpl implements FruitService {

    @Autowired
    private FruitMapper fruitMapper;

    @Autowired
    private HistoryMapper historyMapper;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public void addFruit(Fruit fruit) {
        // 补全属性
        fruit.setFruitId(UUID.randomUUID().toString());
        fruit.setFruitFlag(1);
        fruit.setFruitCreatedTime(DateUtils.newDate());
        fruitMapper.addFruit(fruit);

        // 生成财务记录
        // 默认进货价格是售价的80%
        Double totalPrice = fruit.getFruitPrice() * fruit.getFruitNum() * 0.8;
        historyMapper.pay(totalPrice);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "购入了了水果" + fruit.getFruitName() + " " + fruit.getFruitNum() + "kg";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        History history = HistoryUtils.newHistory(totalPrice, "购入" + fruit.getFruitNum() + "kg" + fruit.getFruitName(), 1);
        historyMapper.addHistory(history);


    }

    @Override
    public Page<Fruit> getFruitList(Page<Fruit> p) {
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

        List<Fruit> fruitList = fruitMapper.getFruitList(p);
        p.setList(fruitList);

        Integer totalCount = fruitMapper.getFruitCount(p);
        p.setTotalCount(totalCount);

        Integer totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
        p.setTotalPage(totalPage);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "查看了水果列表";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);


        return p;
    }

    @Override
    public void deleteFruitById(String fruitId) {
        Fruit fruit = fruitMapper.getFruitById(fruitId);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "删除了水果" + fruit.getFruitName();
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        fruitMapper.deleteFruitById(fruitId);
    }

    @Override
    public Fruit getFruitById(String fruitId) {
        return fruitMapper.getFruitById(fruitId);
    }

    @Override
    public void editFruit(Fruit fruit) {
        // 修改属性
        Fruit f = fruitMapper.getFruitById(fruit.getFruitId());
        fruit.setFruitCreatedTime(DateUtils.newDate());

        //如果数量不为空，就增加这个数量
        if (fruit.getFruitNum() != null) {
            Double totalPrice = f.getFruitPrice() * fruit.getFruitNum() * 0.8;
            historyMapper.pay(totalPrice);
            History history = HistoryUtils.newHistory(totalPrice, "购入" + fruit.getFruitNum() + "kg" + fruit.getFruitName(), 1);
            historyMapper.addHistory(history);


            String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
            String time = DateUtils.newDate();
            String article = staffName + "在" + time + "购入了水果" + fruit.getFruitName() + fruit.getFruitNum() + "kg";
            Log log = LogUtils.newLog(time, article);
            logMapper.addLog(log);
            fruit.setFruitNum(f.getFruitNum() + fruit.getFruitNum());

        } else {
            String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
            String time = DateUtils.newDate();
            String article = staffName + "在" + time + "编辑了水果" + f.getFruitName() +
                    "，水果名从" + f.getFruitName() + "改成了" + fruit.getFruitName() +
                    "，单价从" + f.getFruitPrice() + "改成了" + fruit.getFruitPrice();
            Log log = LogUtils.newLog(time, article);
            logMapper.addLog(log);
        }

        fruitMapper.editFruit(fruit);
    }

    @Override
    public Fruit getFruitFromCartByFruitId(Fruit fruit) {
        return fruitMapper.getFruitFromCartByFruitId(fruit);
    }

    @Override
    public List<Fruit> getAllFruitById() {
        return fruitMapper.getAllFruitById();
    }

}
