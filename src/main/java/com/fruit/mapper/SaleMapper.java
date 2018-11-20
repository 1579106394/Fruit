package com.fruit.mapper;

import com.fruit.pojo.Sale;
import com.fruit.utils.Page;

import java.util.List;

public interface SaleMapper {

    // 新增销售记录
    void addSale(Sale sale);

    // 获取数据数量
    Integer getSaleCount(Page<Sale> p);

    // 获取销售列表
    List<Sale> getSaleList(Page<Sale> p);

    // 根据id删除销售记录
    void deleteSaleById(String saleId);

    // 查询所有水果的销量
    List<Sale> getAllSale();

    Integer getSaleNum();
}
