package com.fruit.service;

import com.fruit.pojo.Fruit;
import com.fruit.utils.Page;

import java.util.List;

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

    // 从购物车中查询水果是否存在
    Fruit getFruitFromCartByFruitId(Fruit fruit);

    // 查询所有的水果
    List<Fruit> getAllFruitById();
}
