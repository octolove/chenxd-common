package com.cxd.cool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

@Service
public class ElastService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

}
