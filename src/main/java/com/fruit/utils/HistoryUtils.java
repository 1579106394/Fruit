package com.fruit.utils;

import com.fruit.pojo.History;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 用于生成记录实体类的工具类
 */
@Service
public class HistoryUtils {


    public static History newHistory(Double price, String actionName, Integer flag) {
        History history = new History();
        // 补全属性
        history.setHistoryId(UUID.randomUUID().toString());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String time = sdf.format(new Date());
        history.setHistoryCreatedTime(time);

        history.setHistoryPrice(price);

        history.setHistoryAction(actionName);

        history.setHistoryFlag(flag);

        history.setHistoryDelete(1);

        return history;
    }

}
