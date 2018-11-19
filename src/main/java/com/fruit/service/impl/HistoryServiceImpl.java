package com.fruit.service.impl;

import com.fruit.mapper.HistoryMapper;
import com.fruit.mapper.LogMapper;
import com.fruit.pojo.History;
import com.fruit.pojo.Log;
import com.fruit.pojo.Staff;
import com.fruit.service.HistoryService;
import com.fruit.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private JedisClient jedisClient;

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

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "查看了财务记录";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        return p;
    }

    @Override
    public History getHistoryById(String historyId) {
        return historyMapper.getHistoryById(historyId);
    }

    @Override
    public void deleteHistoryById(String historyId) {

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "删除了财务记录，ID为 " + historyId;
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        historyMapper.deleteHistoryById(historyId);
    }

    @Override
    public Double getAdminPrice() {
        return historyMapper.getAdminPrice();
    }

}
