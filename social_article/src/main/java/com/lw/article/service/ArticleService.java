package com.lw.article.service;

import com.lw.article.entity.Article;
import entity.PageUtil;

import java.util.List;
import java.util.Map;

/**
 * @author lanwei
 * @email 335747949@qq.com
 */
public interface ArticleService {

    List<Article> selectList();

    Article getOne(long id);

    int update(Article article);

    int save(Article article);

    int delete(String id);

    PageUtil queryPage(Map<String ,Object> param);
}
