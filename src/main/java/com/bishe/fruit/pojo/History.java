package com.bishe.fruit.pojo;

import java.io.Serializable;

/**
 * CREATE TABLE `history` (
 *   `history_id` varchar(36) NOT NULL,
 *   `history_created_time` varchar(36) NOT NULL COMMENT '产生时间',
 *   `history_pirce` double(10,0) NOT NULL DEFAULT '0' COMMENT '多少钱 ',
 *   `history_action` varchar(36) NOT NULL COMMENT '用于做什么，进货？工资？',
 *   `history_flag` int(2) NOT NULL DEFAULT '1' COMMENT '1支出2进入',
 *   `history_delete` int(2) NOT NULL DEFAULT '1' COMMENT '1正常2删除',
 *   PRIMARY KEY (`history_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class History implements Serializable {

    private String historyId;
    private String historyCreatedTime;
    private Double historyPrice;
    private Action action;
    private Integer historyFlag;
    private Integer historyDelete;

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public String getHistoryCreatedTime() {
        return historyCreatedTime;
    }

    public void setHistoryCreatedTime(String historyCreatedTime) {
        this.historyCreatedTime = historyCreatedTime;
    }

    public Double getHistoryPrice() {
        return historyPrice;
    }

    public void setHistoryPrice(Double historyPrice) {
        this.historyPrice = historyPrice;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Integer getHistoryFlag() {
        return historyFlag;
    }

    public void setHistoryFlag(Integer historyFlag) {
        this.historyFlag = historyFlag;
    }

    public Integer getHistoryDelete() {
        return historyDelete;
    }

    public void setHistoryDelete(Integer historyDelete) {
        this.historyDelete = historyDelete;
    }
}
