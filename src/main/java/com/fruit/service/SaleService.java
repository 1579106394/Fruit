package com.fruit.service;

import com.fruit.pojo.Sale;
import com.fruit.utils.Page;

public interface SaleService {

    // 查看销售记录列表
    Page<Sale> getSaleList(Page<Sale> p);

    // 根据id删除销售记录
    void deleteSaleById(String saleId);
}
