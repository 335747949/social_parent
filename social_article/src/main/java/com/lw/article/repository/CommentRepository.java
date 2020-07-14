package com.lw.article.repository;

import com.lw.article.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author lanwei
 * @email 335747949@qq.com
 */
public interface CommentRepository extends MongoRepository<Comment,String>{
}
