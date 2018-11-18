package com.fruit.utils;

import com.fruit.pojo.Log;

import java.util.UUID;

public class LogUtils {


    public static Log newLog(String article) {
        Log log = new Log();
        log.setLogId(UUID.randomUUID().toString());
        log.setLogCreatedTime(DateUtils.newDate());
        log.setLogArticle(article);
        log.setLogFlag(1);

        return log;
    }


}
