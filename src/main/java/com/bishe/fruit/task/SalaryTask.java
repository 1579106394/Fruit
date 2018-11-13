package com.bishe.fruit.task;

import com.bishe.fruit.mapper.HistoryMapper;
import com.bishe.fruit.mapper.StaffMapper;
import com.bishe.fruit.pojo.History;
import com.bishe.fruit.pojo.Staff;
import com.bishe.fruit.utils.HistoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryTask {

    @Autowired
    private HistoryMapper historyMapper;

    @Autowired
    private StaffMapper staffMapper;

    /**
     * 定时任务，每个月15号给员工发工资
     */
    @Scheduled(cron = "0 0 1 15 * ?")
    public void addSalary() {
        List<Staff> staffList = staffMapper.getAllStaff();
        for (Staff staff : staffList) {
            History history = HistoryUtils.newHistory(staff.getSalary().getSalaryPrice(), "给员工" +
                    staff.getStaffName() + "发工资" + staff.getSalary().getSalaryPrice() + "元", 1);
            historyMapper.addHistory(history);
            historyMapper.pay(staff.getSalary().getSalaryPrice());
        }
    }

}
