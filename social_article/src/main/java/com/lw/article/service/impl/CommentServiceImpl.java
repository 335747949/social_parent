package com.lw.article.service.impl;

import com.lw.article.entity.Comment;
import com.lw.article.repository.CommentRepository;
import com.lw.article.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author lanwei
 * @email 335747949@qq.com
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private IdWorker idWorker;

    @Override
    public List<Comment> findAll() {
        List<Comment> all = commentRepository.findAll();
        return all;
    }

    @Override
    public Comment findById(String commentid) {
        Optional<Comment> optional = commentRepository.findById(commentid);
        if(optional.isPresent()){
           return optional.get();
        }

//        Comment comment = commentRepository.findById(commentid).get();    **不建议这样写会出现一些意想不到的异常
        return null;
    }

    @Override
    public void save(Comment comment) {
        //使用分布式id生成器
        String id = idWorker.nextId()+ "";

        //初始化数据
        comment.set_id(id);
        comment.setPublishdate(new Date());
        comment.setThumbup(0);

        //保存数据
        commentRepository.save(comment);
    }

    @Override
    public void update(Comment comment) {
        //有主键则为修改，没主键则为新增
        commentRepository.save(comment);
    }

    @Override
    public void deleteById(String commentid) {
        commentRepository.deleteById(commentid);
    }


}
