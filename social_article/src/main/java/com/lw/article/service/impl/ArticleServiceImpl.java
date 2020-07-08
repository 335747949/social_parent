package com.lw.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lw.article.dao.ArticleMapper;
import com.lw.article.entity.Article;
import com.lw.article.service.ArticleService;
import entity.PageUtil;
import entity.QueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.security.Key;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lanwei
 * @email 335747949@qq.com
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private IdWorker idWorker;


    @Override
    public List<Article> selectList() {
        return articleMapper.selectList(null);
    }

    @Override
    public Article getOne(long id) {
        return articleMapper.selectById(id);
    }

    @Override
    public int update(Article article) {
        return articleMapper.updateById(article);
    }

    @Override
    public int save(Article article) {
        //使用分布式id生成器
        String id = idWorker.nextId()+ "";
        article.setId(id);

        //初始化数据  浏览量 点赞数 评论数
        article.setVisits(0);
        article.setComment(0);
        article.setThumbup(0);

        return articleMapper.insert(article);
    }

    @Override
    public int delete(String id) {
        return articleMapper.deleteById(id);
    }

    @Override
    public PageUtil queryPage(Map<String, Object> param) {

        //设置查询条件
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        Set<String> set = param.keySet();
        for (String key : set) {
            wrapper.eq(param.get(key) != null,key,param.get(key));
        }

        IPage<Article> queryPage = new QueryUtil<Article>().getQueryPage(param);

        IPage<Article> resPage = articleMapper.selectPage(queryPage, wrapper);

        return new PageUtil(resPage);

    }
}
