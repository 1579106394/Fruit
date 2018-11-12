package com.bishe.fruit.pojo;

import java.io.Serializable;

/**
 * CREATE TABLE `action` (
 *   `action_id` varchar(36) NOT NULL COMMENT '行为id',
 *   `action_name` varchar(36) NOT NULL COMMENT '行为名称（工资？进货？）',
 *   PRIMARY KEY (`action_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class Action implements Serializable {

    private String actionId;
    private String actionName;

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
}
