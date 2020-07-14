package com.lw.article.controller;

import com.lw.article.entity.Comment;
import com.lw.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //新增数据
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result save(@RequestBody Comment comment){
        commentService.save(comment);
        return new Result(true,StatusCode.OK,"新增成功");
    }

    //修改数据
    @RequestMapping(value = "/update/{commentid}",method = RequestMethod.POST)
    public Result update(@PathVariable String commentid,@RequestBody Comment comment) {
        comment.set_id(commentid);
        commentService.update(comment);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    //删除数据
    @RequestMapping(value = "/delete/{commentid}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String commentid){
        commentService.deleteById(commentid);
        return new Result(true,StatusCode.OK,"删除成功");
    }

}
