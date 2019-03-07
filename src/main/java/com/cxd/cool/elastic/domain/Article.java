package com.cxd.cool.elastic.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "blog",
    type = "Article",
    indexStoreType = "fs",
    shards = 5,
    replicas = 1,
    refreshInterval = "-1")
public class Article {

    @Id
    private String id;

    private String title;

    private String article;

    private String author;

    private Integer goods;

    private Integer bads;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getGoods() {
        return goods;
    }

    public void setGoods(Integer goods) {
        this.goods = goods;
    }

    public Integer getBads() {
        return bads;
    }

    public void setBads(Integer bads) {
        this.bads = bads;
    }

    @Override
    public String toString() {
        return "Article [id=" + id + ", title=" + title + ", article=" + article + ", author=" + author + ", goods=" + goods + ", bads=" + bads + "]";
    }
}
