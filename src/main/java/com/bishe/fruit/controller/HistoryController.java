package com.bishe.fruit.controller;

import com.bishe.fruit.pojo.History;
import com.bishe.fruit.service.HistoryService;
import com.bishe.fruit.utils.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/history/")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    /**
     * 支出情况
     * @param model
     * @param p
     * @return
     */
    @RequestMapping("payList.html")
    public String payList(Model model, Page<History> p) {
        p.getParams().put("historyFlag", 1);
        Page<History> page = historyService.getHistoryList(p);
        model.addAttribute("page", page);

        Double adminPrice = historyService.getAdminPrice();
        model.addAttribute("adminPrice", adminPrice);
        return "history/pay-list";
    }

    /**
     * 收入情况
     * @param model
     * @param p
     * @return
     */
    @RequestMapping("incomeList.html")
    public String incomeList(Model model, Page<History> p) {
        p.getParams().put("historyFlag", 2);
        Page<History> page = historyService.getHistoryList(p);
        model.addAttribute("page", page);

        Double adminPrice = historyService.getAdminPrice();
        model.addAttribute("adminPrice", adminPrice);
        return "history/income-list";
    }

    @RequestMapping("deleteHistory{historyId}.html")
    public String deleteHistoryById(@PathVariable String historyId, String[] ids) {
        History history;
        if(StringUtils.isNotBlank(historyId)) {
            history = historyService.getHistoryById(historyId);
            historyService.deleteHistoryById(historyId);
        }else {
            history = historyService.getHistoryById(ids[0]);
            for (String id : ids) {
                historyService.deleteHistoryById(id);
            }
        }
        Integer flag = history.getHistoryFlag();
        if(flag == 1) {
            return "/api/history/payList.html";
        }else {
            return "/api/history/incomeList.html";
        }

    }

}
