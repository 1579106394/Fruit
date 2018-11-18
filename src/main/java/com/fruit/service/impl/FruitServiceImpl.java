package com.fruit.service.impl;

import com.fruit.mapper.FruitMapper;
import com.fruit.mapper.HistoryMapper;
import com.fruit.pojo.Fruit;
import com.fruit.pojo.History;
import com.fruit.service.FruitService;
import com.fruit.utils.DateUtils;
import com.fruit.utils.HistoryUtils;
import com.fruit.utils.Page;
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

        return p;
    }

    @Override
    public void deleteFruitById(String fruitId) {
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

            fruit.setFruitNum(f.getFruitNum() + fruit.getFruitNum());
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
