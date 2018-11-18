package com.fruit.service.impl;

import com.fruit.mapper.SaleMapper;
import com.fruit.pojo.Sale;
import com.fruit.service.SaleService;
import com.fruit.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleMapper saleMapper;

    @Override
    public Page<Sale> getSaleList(Page<Sale> p) {
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

        List<Sale> saleList = saleMapper.getSaleList(p);
        p.setList(saleList);

        Integer totalCount = saleMapper.getSaleCount(p);
        p.setTotalCount(totalCount);

        Integer totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
        p.setTotalPage(totalPage);

        return p;
    }

    @Override
    public void deleteSaleById(String saleId) {
        saleMapper.deleteSaleById(saleId);
    }

}
