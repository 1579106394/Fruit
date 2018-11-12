package com.bishe.fruit.mapper;

import com.bishe.fruit.pojo.History;

public interface HistoryMapper {

    // 支出
    void pay(Double totalPrice);

    // 新增历史
    void addHistory(History history);
}
