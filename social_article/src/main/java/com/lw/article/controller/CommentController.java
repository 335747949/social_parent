package com.lw.article.controller;

import com.lw.article.entity.Comment;
import com.lw.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    private RedisTemplate redisTemplate;

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


    //通过文章id查询评论
    @RequestMapping(value = "/article/{articleid}",method = RequestMethod.GET)
    public Result findByArticleid(@PathVariable String articleid){
        List<Comment> list = commentService.findByArticleid(articleid);
        return new Result(true,StatusCode.OK,"查询成功",list);
    }

    //对评论点赞
    @RequestMapping(value = "/thumbup/{commentid}",method = RequestMethod.PUT)
    public Result tthumbup(@PathVariable String commentid){

        /**
         * 将用户点赞数据存到Redis中
         * 每次点赞前，先查询点赞信息
         * 如果没有点赞信息，则代表可以点赞
         * 如果有点赞信息，则代表不能点赞
         */
        //模拟用户信息
        String userid = "lanwei1234";

        Object flag = redisTemplate.opsForValue().get("thumbup_"+userid+"_"+commentid);
        //thumbup_lanwei1234_1282968963634040832

        if(flag == null) {
            commentService.thumbup(commentid);
            redisTemplate.opsForValue().set("thumbup_"+userid+"_"+commentid,1);
            return new Result(true,StatusCode.OK,"点赞成功");
        }

//        //取消点赞 点赞数-1
//        commentService.noThumbup(commentid);
//        //删除Redis标记
//        redisTemplate.opsForHash().delete("thumbup_"+userid+"_"+commentid);
//        return new Result(true,StatusCode.OK,"取消点赞");

        return new Result(false,StatusCode.REPERROR,"不能重复点赞");
    }

}
