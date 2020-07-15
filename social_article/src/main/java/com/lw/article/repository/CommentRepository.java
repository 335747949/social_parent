package com.lw.article.repository;

import com.lw.article.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

/**
 * @author lanwei
 * @email 335747949@qq.com
 */
public interface CommentRepository extends MongoRepository<Comment,String>{

    //SpringDataMongoDB 支持通过查询方法名进行查询定义方法

//    //根据发布时间和点赞数查询
//    List<Comment> findByPublishdateAndThumbup(Date date,Integer thumbup);
//
//    //根据用户id查询，并根据发布时间倒序排序
//    List<Comment> findByUseridAndOrderByPublishdateDesc(String userid,Date date);

    //根据文章id查询
    List<Comment> findByArticleid(String articleId);
}
