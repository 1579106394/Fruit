package com.fruit.service.impl;

import com.fruit.mapper.HistoryMapper;
import com.fruit.pojo.History;
import com.fruit.service.HistoryService;
import com.fruit.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public Page<History> getHistoryList(Page<History> p) {
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

        List<History> historyList = historyMapper.getHistoryList(p);
        p.setList(historyList);

        Integer totalCount = historyMapper.getHistoryCount(p);
        p.setTotalCount(totalCount);

        Integer totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
        p.setTotalPage(totalPage);

        return p;
    }

    @Override
    public History getHistoryById(String historyId) {
        return historyMapper.getHistoryById(historyId);
    }

    @Override
    public void deleteHistoryById(String historyId) {
        historyMapper.deleteHistoryById(historyId);
    }

    @Override
    public Double getAdminPrice() {
        return historyMapper.getAdminPrice();
    }

}