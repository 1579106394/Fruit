package com.bishe.fruit.mapper;

import com.bishe.fruit.pojo.Fruit;
import com.bishe.fruit.utils.Page;

import java.util.List;

public interface FruitMapper {

    // 添加水果
    void addFruit(Fruit fruit);

    // 查询水果列表
    List<Fruit> getFruitList(Page<Fruit> p);

    // 查询水果总数
    Integer getFruitCount(Page<Fruit> p);

    // 根据id删除水果
    void deleteFruitById(String fruitId);

    // 根据id获取水果
    Fruit getFruitById(String fruitId);

    // 编辑水果
    void editFruit(Fruit fruit);
}
