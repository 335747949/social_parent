package com.lw.article.service.impl;

import com.lw.article.entity.Comment;
import com.lw.article.repository.CommentRepository;
import com.lw.article.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
    @Autowired
    private MongoTemplate mongoTemplate;

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


    @Override
    public List<Comment> findByArticleid(String articleId) {

        //调用持久层，根据文章id查询评论数
        List<Comment> comments = commentRepository.findByArticleid(articleId);
        return comments;
    }

    @Override
    public void thumbup(String commentid) {
//        //根据评论id，拿到评论数据
//        Comment comment = commentRepository.findById(commentid).get();
//        //点赞数+1
//        comment.setThumbup(comment.getThumbup()+1);
//        //保存修改点赞数
//        commentRepository.save(comment);
//        //****   这种写法是线程不安全的


        //点赞功能优化  使用mongodb模板
        //封装查询条件
        Query query = new Query().addCriteria(Criteria.where("_id").is(commentid));

        //封装修改数据
        //使用inc 列值增长
        Update update = new Update().inc("thumbup",1);

        //使用mongo模板直接修改数据
        //第一个参数where条件
        //第二个参数修改的数据
        //第三个参数mongodb的集合名称
        mongoTemplate.updateFirst(query,update,"comment");
    }

}
