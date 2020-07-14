package com.lw.article.service;

import com.lw.article.entity.Comment;

import java.util.List;

/**
 * @author lanwei
 * @email 335747949@qq.com
 */
public interface CommentService {

    List<Comment> findAll();


    Comment findById(String commentid);
}
