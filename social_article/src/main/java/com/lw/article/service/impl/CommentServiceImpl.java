package com.lw.article.service.impl;

import com.lw.article.entity.Comment;
import com.lw.article.repository.CommentRepository;
import com.lw.article.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lanwei
 * @email 335747949@qq.com
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        List<Comment> all = commentRepository.findAll();
        return all;
    }

    @Override
    public Comment findById(String commentid) {
        Comment comment = commentRepository.findById(commentid).get();
        return comment;
    }


}
