package com.bishe.fruit.pojo;

import java.io.Serializable;

/**
 * CREATE TABLE `comment` (
 *   `comment_id` varchar(36) NOT NULL,
 *   `comment_staff` varchar(36) NOT NULL COMMENT '评论用户',
 *   `comment_article` mediumtext NOT NULL COMMENT '评论内容',
 *   `comment_order` varchar(36) NOT NULL COMMENT '评论哪个订单',
 *   `comment_crearted_time` varchar(36) NOT NULL COMMENT '评论时间',
 *   `comment_flag` int(2) NOT NULL DEFAULT '1' COMMENT '1正常2删除',
 *   PRIMARY KEY (`comment_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class Comment implements Serializable {

    private String commentId;
    private Staff staff;
    private String commentArticle;
    private Order order;
    private String commentCreatedTime;
    private Integer commentFlag;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getCommentArticle() {
        return commentArticle;
    }

    public void setCommentArticle(String commentArticle) {
        this.commentArticle = commentArticle;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getCommentCreatedTime() {
        return commentCreatedTime;
    }

    public void setCommentCreatedTime(String commentCreatedTime) {
        this.commentCreatedTime = commentCreatedTime;
    }

    public Integer getCommentFlag() {
        return commentFlag;
    }

    public void setCommentFlag(Integer commentFlag) {
        this.commentFlag = commentFlag;
    }
}
