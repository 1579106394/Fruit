package com.fruit.service;

import com.fruit.pojo.History;
import com.fruit.utils.Page;

public interface HistoryService {

    // 获取财务报表
    Page<History> getHistoryList(Page<History> p);

    // 根据id获取财务列表
    History getHistoryById(String historyId);

    // 根据id删除财务历史
    void deleteHistoryById(String historyId);

    // 获取公司账户
    Double getAdminPrice();

    // 获取所有的支出
    Double getAllPay();

    // 获取所有的收入
    Double getAllCollect();
}
