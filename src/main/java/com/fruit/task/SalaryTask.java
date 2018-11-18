package com.fruit.task;

import com.fruit.mapper.HistoryMapper;
import com.fruit.mapper.StaffMapper;
import com.fruit.pojo.History;
import com.fruit.pojo.Staff;
import com.fruit.utils.HistoryUtils;
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
     * 定时任务，上面的是每月15号发工资，下面的每秒发一次工资
     */
    @Scheduled(cron = "0 0 1 15 * ?")
    //@Scheduled(initialDelay = 1000, fixedDelay = 1000)
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
