package com.bishe.fruit.controller;

import com.bishe.fruit.pojo.Comment;
import com.bishe.fruit.pojo.Staff;
import com.bishe.fruit.service.CommentService;
import com.bishe.fruit.utils.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("api/comment/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 查询评论列表
     * @param model
     * @param p
     * @return
     */
    @RequestMapping("commentList.html")
    public String commentList(Model model, Page<Comment> p) {

        Page<Comment> page = commentService.getCommentList(p);

        model.addAttribute("page", page);
        return "comment/comment-list";
    }

    /**
     * 发表评论
     * @param comment
     * @param session
     * @return
     */
    @RequestMapping("addComment.html")
    public String addComment(Comment comment, HttpSession session) {
        Staff staff = (Staff) session.getAttribute("staff");
        comment.setStaff(staff);
        commentService.addComment(comment);
        return "redirect:/api/comment/commentList.html";
    }

    @RequestMapping("deleteComment{commentId}.html")
    public String deleteComment(@PathVariable String commentId, String[] ids) {
        if(StringUtils.isNotBlank(commentId)) {
            commentService.deleteCommentById(commentId);
        }else {
            for (String id : ids) {
                commentService.deleteCommentById(id);
            }
        }
        return "redirect:/api/comment/commentList.html";
    }


}
