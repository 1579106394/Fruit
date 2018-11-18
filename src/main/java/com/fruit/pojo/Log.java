package com.fruit.pojo;

import java.io.Serializable;

/**
 * CREATE TABLE `log` (
 *   `log_id` varchar(36) NOT NULL,
 *   `log_created_time` varchar(36) NOT NULL,
 *   `log_article` mediumtext NOT NULL,
 *   `log_flag` int(2) NOT NULL DEFAULT '1' COMMENT '1正常2删除',
 *   PRIMARY KEY (`log_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class Log implements Serializable {

    private String logId;
    private String logCreatedTime;
    private String logArticle;
    private Integer logFlag;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getLogCreatedTime() {
        return logCreatedTime;
    }

    public void setLogCreatedTime(String logCreatedTime) {
        this.logCreatedTime = logCreatedTime;
    }

    public String getLogArticle() {
        return logArticle;
    }

    public void setLogArticle(String logArticle) {
        this.logArticle = logArticle;
    }

    public Integer getLogFlag() {
        return logFlag;
    }

    public void setLogFlag(Integer logFlag) {
        this.logFlag = logFlag;
    }
}
