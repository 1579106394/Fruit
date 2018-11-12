package com.bishe.fruit.service.impl;

import com.bishe.fruit.mapper.FruitMapper;
import com.bishe.fruit.mapper.HistoryMapper;
import com.bishe.fruit.pojo.Fruit;
import com.bishe.fruit.pojo.History;
import com.bishe.fruit.service.FruitService;
import com.bishe.fruit.utils.DateUtils;
import com.bishe.fruit.utils.HistoryUtils;
import com.bishe.fruit.utils.Page;
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

        History history = HistoryUtils.newHistory(totalPrice, "进货", 1);
        historyMapper.addHistory(history);

    }

    @Override
    public Page<Fruit> getFruitList(Page<Fruit> p) {
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

        List<Fruit> fruitList = fruitMapper.getFruitList(p);
        p.setList(fruitList);

        Integer totalCount = fruitMapper.getFruitCount(p);
        p.setTotalCount(totalCount);

        Integer totalPage = (int)Math.ceil(totalCount * 1.0 / currentCount);
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
        if(fruit.getFruitNum() != null) {
            Double totalPrice = f.getFruitPrice() * fruit.getFruitNum() * 0.8;
            historyMapper.pay(totalPrice);
            History history = HistoryUtils.newHistory(totalPrice, "进货", 1);
            historyMapper.addHistory(history);

            fruit.setFruitNum(f.getFruitNum() + fruit.getFruitNum());
        }

        fruitMapper.editFruit(fruit);
    }

}
