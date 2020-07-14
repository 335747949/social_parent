package com.lw.article.controller;

import com.lw.article.entity.Comment;
import com.lw.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lanwei
 * @email 335747949@qq.com
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //查询所有数据
    @RequestMapping("/list")
    public Result list(){
        List<Comment> list = commentService.findAll();
        return new Result(true, StatusCode.OK,"查询成功",list);
    }

    @RequestMapping("/list/{commentid}")
    public Result listOne(@PathVariable String commentid){
        Comment comment = commentService.findById(commentid);
        return new Result(true,StatusCode.OK,"查询成功",comment);
    }

}
