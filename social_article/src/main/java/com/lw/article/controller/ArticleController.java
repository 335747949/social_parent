package com.lw.article.controller;

import com.lw.article.entity.Article;
import com.lw.article.service.ArticleService;
import entity.PageUtil;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lanwei
 * @email 335747949@qq.com
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/list")
    public Result list(){
        List<Article> articles = articleService.selectList();
        return new Result(true, StatusCode.OK,"查询成功",articles);
    }

    @RequestMapping("/save")
    public Result save(@RequestBody Article article){
        int save = articleService.save(article);
        return new Result(true,StatusCode.OK,"新增成功");
    }

    @RequestMapping("/info/{id}")
    public Result info(@PathVariable Long id){
        Article one = articleService.getOne(id);
        return new Result(true,StatusCode.OK,"查询成功",one);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Article article){
        int update = articleService.update(article);
        return new Result(true,StatusCode.OK,"修改成功",update);
    }

    @RequestMapping("/delete/{id}")
    public Result delete(@PathVariable String id){
        articleService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @RequestMapping("/list1")
    public Result list(@RequestParam Map<String,Object> param){

        Map<String,Object> page = new HashMap<>();
        PageUtil pageUtil = articleService.queryPage(param);
        page.put("page",pageUtil);
        return new Result(true, StatusCode.OK,"查询成功",page);
    }
}
