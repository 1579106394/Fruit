package com.bishe.fruit.mapper;

import com.bishe.fruit.pojo.Comment;
import com.bishe.fruit.utils.Page;

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
}
