package com.fruit.service.impl;

import com.fruit.mapper.CommentMapper;
import com.fruit.pojo.Comment;
import com.fruit.service.CommentService;
import com.fruit.utils.DateUtils;
import com.fruit.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Page<Comment> getCommentList(Page<Comment> p) {
        if (p.getCurrentPage() == null) {
            p.setCurrentPage(1);
        }

        if (p.getCurrentCount() == null) {
            p.setCurrentCount(10);
        }

        Integer currentPage = p.getCurrentPage();
        Integer currentCount = p.getCurrentCount();

        Integer index = (currentPage - 1) * currentCount;
        p.setIndex(index);

        List<Comment> commentList = commentMapper.getCommentList(p);
        p.setList(commentList);

        Integer totalCount = commentMapper.getCommentCount(p);
        p.setTotalCount(totalCount);

        Integer totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
        p.setTotalPage(totalPage);

        return p;
    }

    @Override
    public void addComment(Comment comment) {
        // 补全属性
        comment.setCommentId(UUID.randomUUID().toString());
        comment.setCommentCreatedTime(DateUtils.newDate());
        comment.setCommentFlag(1);
        commentMapper.addComment(comment);
    }

    @Override
    public void deleteCommentById(String commentId) {
        commentMapper.deleteCommentById(commentId);
    }

    @Override
    public Comment getCommentById(String commentId) {
        return commentMapper.getCommentById(commentId);
    }

    @Override
    public void editComment(Comment comment) {
        commentMapper.editComment(comment);
    }

}
