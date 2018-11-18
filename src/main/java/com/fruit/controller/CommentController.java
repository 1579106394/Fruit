package com.fruit.controller;

import com.fruit.pojo.Comment;
import com.fruit.pojo.Staff;
import com.fruit.service.CommentService;
import com.fruit.utils.Page;
import com.fruit.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String commentList(Model model, Page<Comment> p, HttpSession session) {
        Staff staff = (Staff) session.getAttribute("staff");
        if(staff.getStaffRole() == 2) {
            p.getParams().put("staff", staff);
        }
        Page<Comment> page = commentService.getCommentList(p);

        model.addAttribute("page", page);
        return "comment/comment-list";
    }

    /**
     * ajax查看评论
     * @param comment
     * @return
     */
    @RequestMapping("readComment.action")
    @ResponseBody
    public Result readComment(@RequestBody Comment comment) {
        Comment c = commentService.getCommentById(comment.getCommentId());
        return Result.ok(c);
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

    /**
     * 删除评论
     * @param commentId
     * @param ids
     * @return
     */
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

    /**
     * 跳转到编辑页面
     * @param commentId
     * @param model
     * @return
     */
    @RequestMapping("toEdit{commentId}.html")
    public String toEdit(@PathVariable String commentId, Model model) {
        Comment comment = commentService.getCommentById(commentId);
        model.addAttribute("comment", comment);
        return "comment/comment-edit";
    }

    /**
     * 编辑评论
     * @param comment
     * @return
     */
    @RequestMapping("editComment.html")
    public String editComment(Comment comment) {
        commentService.editComment(comment);
        return "redirect:/api/comment/commentList.html";
    }

}
