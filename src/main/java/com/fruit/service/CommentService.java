package com.fruit.service;

import com.fruit.pojo.Comment;
import com.fruit.utils.Page;

public interface CommentService {

    // 获取评论列表
    Page<Comment> getCommentList(Page<Comment> p);

    // 添加评论
    void addComment(Comment comment);

    // 根据id删除评论
    void deleteCommentById(String commentId);

    // 根据id获取评论
    Comment getCommentById(String commentId);

    // 编辑评论
    void editComment(Comment comment);
}
