package com.bishe.fruit.service;

import com.bishe.fruit.pojo.Fruit;
import com.bishe.fruit.utils.Page;

public interface FruitService {

    // 添加水果
    void addFruit(Fruit fruit);

    // 分页查询水果
    Page<Fruit> getFruitList(Page<Fruit> p);

    // 根据id删除水果
    void deleteFruitById(String fruitId);

    // 根据id获取水果
    Fruit getFruitById(String fruitId);

    // 编辑水果
    void editFruit(Fruit fruit);
}
