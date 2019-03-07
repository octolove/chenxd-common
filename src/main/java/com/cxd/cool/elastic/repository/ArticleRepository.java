package com.cxd.cool.elastic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.cxd.cool.elastic.domain.Article;

public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

}
