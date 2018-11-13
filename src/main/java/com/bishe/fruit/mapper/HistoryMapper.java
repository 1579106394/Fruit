package com.bishe.fruit.mapper;

import com.bishe.fruit.pojo.History;
import com.bishe.fruit.utils.Page;

import java.util.List;

public interface HistoryMapper {

    // 支出
    void pay(Double totalPrice);

    // 新增历史
    void addHistory(History history);

    // 获取财务历史列表
    List<History> getHistoryList(Page<History> p);

    // 获取财务历史数量
    Integer getHistoryCount(Page<History> p);

    // 根据id删除财务历史
    void deleteHistoryById(String historyId);

    // 根据id获取财务历史
    History getHistoryById(String historyId);

    // 获取公司账户
    Double getAdminPrice();
}
