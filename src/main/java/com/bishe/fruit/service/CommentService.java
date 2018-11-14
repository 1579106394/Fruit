package com.bishe.fruit.service;

import com.bishe.fruit.pojo.Comment;
import com.bishe.fruit.utils.Page;

public interface CommentService {

    // 获取评论列表
    Page<Comment> getCommentList(Page<Comment> p);

    // 添加评论
    void addComment(Comment comment);

    // 根据id删除评论
    void deleteCommentById(String commentId);
}
