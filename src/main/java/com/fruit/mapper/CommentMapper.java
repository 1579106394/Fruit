package com.fruit.mapper;

import com.fruit.pojo.Comment;
import com.fruit.utils.Page;

import java.util.List;

public interface CommentMapper {

    // 获取评论列表
    List<Comment> getCommentList(Page<Comment> p);

    // 获取评论数量
    Integer getCommentCount(Page<Comment> p);

    // 添加评论
    void addComment(Comment comment);

    // 根据id删除评论
    void deleteCommentById(String commentId);

    // 根据id获取评论
    Comment getCommentById(String commentId);

    // 编辑评论
    void editComment(Comment comment);
}
