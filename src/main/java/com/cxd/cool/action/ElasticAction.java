package com.cxd.cool.action;

import java.util.List;
import java.util.Optional;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.RegexpQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cxd.cool.elastic.domain.Article;
import com.cxd.cool.elastic.repository.ArticleRepository;

@RestController
@RequestMapping(value = "/es")
public class ElasticAction {

    private Logger logger = LoggerFactory.getLogger(ElasticAction.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @RequestMapping(value = "/add")
    public String add() {
        try {
            Article article1 = new Article();

            article1.setId("1");
            article1.setArticle("在一些随机试验中，结果可以用数值来表示，此时样本空间S的元素是数字；但是，有些试验，当样本空间S的元素不是数字时，就需要引入随机变量的概念了");
            article1.setTitle("随机变量及其分布");
            article1.setAuthor("cxd");
            article1.setGoods(1);
            article1.setBads(2);

            articleRepository.save(article1);
        } catch (Exception e) {
            logger.error("error={}", e.getMessage());
        }
        return "OK";
    }

    @RequestMapping(value = "/search/{id}")
    public Article search(@PathVariable("id") String id) {

        Optional<Article> optional = null;
        try {
            optional = articleRepository.findById(id);
        } catch (Exception e) {
            logger.error("err:{}", e);
        }
        return optional.isPresent() ? optional.get() : new Article();
    }

    @RequestMapping(value = "/searchTitle/{title}")
    public Page<Article> searchTitle(@PathVariable("title") String title) {
        SearchQuery query = new NativeSearchQueryBuilder().withFilter(new QueryStringQueryBuilder("变量")).build();
        Page<Article> page = articleRepository.search(query);
        return page;
    }

    @RequestMapping(value = "/searchByTitle/{title}")
    public List<Article> searchByTitle(@PathVariable("title") String title) {
        SearchQuery query = new NativeSearchQueryBuilder().withFilter(new RegexpQueryBuilder("title", "*变量*")).build();
        List<Article> articles = esTemplate.queryForList(query, Article.class);
        return articles;
    }
}
