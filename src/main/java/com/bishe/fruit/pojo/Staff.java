package com.bishe.fruit.pojo;

import java.io.Serializable;

/**
 * CREATE TABLE `staff` (
 *   `staff_id` varchar(36) NOT NULL,
 *   `staff_name` varchar(36) NOT NULL,
 *   `staff_account` varchar(36) NOT NULL COMMENT '账号',
 *   `staff_password` varchar(36) NOT NULL,
 *   `staff_birth` varchar(36) NOT NULL COMMENT '员工生日',
 *   `staff_sex` int(2) NOT NULL DEFAULT '1' COMMENT '1男2女',
 *   `staff_age` int(3) NOT NULL DEFAULT '18' COMMENT '年龄',
 *   `staff_telephone` bigint(12) NOT NULL COMMENT '手机号',
 *   `staff_role` int(3) NOT NULL DEFAULT '1' COMMENT '1员工，2用户，3管理员',
 *   `staff_flag` int(2) NOT NULL DEFAULT '1' COMMENT '1正常2删除',
 *   PRIMARY KEY (`staff_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class Staff implements Serializable {

    private String staffId;
    private String staffName;
    private String staffAccount;
    private String staffPassword;
    private String staffBirth;
    private Integer staffSex;
    private Integer staffAge;
    private Long staffTelephone;
    private Integer staffRole;
    private Integer staffFlag;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffAccount() {
        return staffAccount;
    }

    public void setStaffAccount(String staffAccount) {
        this.staffAccount = staffAccount;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }

    public String getStaffBirth() {
        return staffBirth;
    }

    public void setStaffBirth(String staffBirth) {
        this.staffBirth = staffBirth;
    }

    public Integer getStaffSex() {
        return staffSex;
    }

    public void setStaffSex(Integer staffSex) {
        this.staffSex = staffSex;
    }

    public Integer getStaffAge() {
        return staffAge;
    }

    public void setStaffAge(Integer staffAge) {
        this.staffAge = staffAge;
    }

    public Long getStaffTelephone() {
        return staffTelephone;
    }

    public void setStaffTelephone(Long staffTelephone) {
        this.staffTelephone = staffTelephone;
    }

    public Integer getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(Integer staffRole) {
        this.staffRole = staffRole;
    }

    public Integer getStaffFlag() {
        return staffFlag;
    }

    public void setStaffFlag(Integer staffFlag) {
        this.staffFlag = staffFlag;
    }
}
