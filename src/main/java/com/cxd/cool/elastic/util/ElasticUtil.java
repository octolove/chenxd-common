package com.cxd.cool.elastic.util;

import java.io.IOException;

import org.elasticsearch.action.DocWriteRequest.OpType;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class ElasticUtil {

    @Autowired
    private RestHighLevelClient client;

    /**
     * 判断idnex是否存在
     *
     * @param indexName
     * @return
     * @throws IOException`
     */
    public boolean existsIndex(String indexName) throws IOException {
        GetIndexRequest request = new GetIndexRequest();
        request.indices(indexName);
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println("existsIndex: " + exists);
        return exists;
    }

    /**
     * 创建index
     *
     * @param indexName
     * @return
     * @throws IOException
     */
    public CreateIndexResponse createIndec(String indexName) throws IOException {
        if (!existsIndex(indexName)) {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
            createIndexRequest.settings(Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 1));
            // 设置索引的别名
            // createIndexRequest.alias(new Alias("mmm"));
            CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            return createIndexResponse;
        }
        return null;
    }

    /**
     * 保存数据
     *
     * @param index
     * @param type
     * @param t
     * @param uid
     */
    public <T> IndexResponse add(String index, String type, T t, String uid) throws IOException {
        IndexRequest indexRequest = new IndexRequest(index, type, uid);
        indexRequest.source(JSON.toJSONString(t), XContentType.JSON);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        return indexResponse;
    }

    /**
     * 根据主键查询记录
     *
     * @param index
     * @param type
     * @param id
     * @throws IOException
     */
    public GetResponse get(String index, String type, String id) throws IOException {
        GetRequest getRequest = new GetRequest(index, type, id);

        /**
         * 选择返回的字段
         * String[] includes = new String[] { "message", "*Date" };
         * String[] excludes = Strings.EMPTY_ARRAY;
         * FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
         * getRequest.fetchSourceContext(fetchSourceContext);
         */
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        return getResponse;
    }

    /**
     * 根据ID删除
     *
     * @param index
     * @param type
     * @param id
     * @throws IOException
     */
    public DeleteResponse delete(String index, String type, String id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(index, type, id);
        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        return deleteResponse;
    }

    /**
     * 删除index
     *
     * @param index
     * @param type
     * @param id
     * @throws IOException
     */
    public DeleteResponse delete(String index) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(index);
        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        return deleteResponse;
    }

    /**
     * 创建mapping
     *
     * @param indexName
     * @param mappingType
     * @return
     * @throws IOException
     */
    public void createMapping(String indexName, String mappingType) throws IOException {

        XContentBuilder mapping = XContentFactory.jsonBuilder().startObject().startObject("properties")

        .startObject("uid").field("type", "keyword").field("index", "false").endObject() // 默认true

            .startObject("name").field("type", "keyword").field("index", "true").endObject()

            .startObject("age").field("type", "byte").field("index", "true").endObject()

            .startObject("phone").field("type", "keyword").field("index", "true").endObject()

            .startObject("address").field("type", "text").field("index", "true").field("analyzer", "ik_max_word").field("search_analyzer", "ik_max_word").endObject()

            .startObject("birtydate").field("type", "date").field("index", "true").endObject()

            // .startObject("birtydate").field("type", "date").field("index",
            // "true").field("format","yyyy-MM-dd HH:mm:ss").endObject()

            .endObject().endObject();

        PutMappingRequest request = new PutMappingRequest(indexName).type(mappingType).source(mapping);
        boolean flag = client.indices().putMapping(request, RequestOptions.DEFAULT).isAcknowledged();
        System.out.println("------------------->>>>>" + flag);
    }

    /**
     * 批量保存数据
     *
     * @param index
     * @param type
     * @param t
     * @param uid
     */
    public <T> BulkResponse bulkAdd(String index, String type, T t, String uid) throws IOException {
        BulkRequest request = new BulkRequest();

        IndexRequest indexRequest = new IndexRequest(index, type, uid);
        indexRequest.source(JSON.toJSONString(t), XContentType.JSON);
        IndexRequest indexRequest2 = new IndexRequest(index, type, uid);
        indexRequest2.source(JSON.toJSONString(t), XContentType.JSON);

        request.add(indexRequest);
        request.add(indexRequest2);

        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        for (BulkItemResponse bulkItemResponse : bulkResponse) {
            DocWriteResponse itemResponse = bulkItemResponse.getResponse();
            if (bulkItemResponse.getOpType() == OpType.INDEX || bulkItemResponse.getOpType() == OpType.CREATE) {
                IndexResponse indexResponse = (IndexResponse) itemResponse;
                // 新增成功的处理

            } else if (bulkItemResponse.getOpType() == OpType.UPDATE) {
                UpdateResponse updateResponse = (UpdateResponse) itemResponse;
                // 修改成功的处理

            } else if (bulkItemResponse.getOpType() == OpType.DELETE) {
                DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
                // 删除成功的处理
            }
        }

        return bulkResponse;
    }

    /**
     * 查询
     *
     * @param index
     * @return void
     * @throws IOException
     * @throws
     */
    public void query(String index, QueryBuilder queryBuilder) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 分页 从0开始取5条
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(5);

        /**
         * // 获取指定字段
         * searchSourceBuilder.fetchSource("name", null);
         * 
         * // 指定排序
         * searchSourceBuilder.sort(new FieldSortBuilder("_uid").order(SortOrder.ASC));
         * 
         * // 高亮设置
         * HighlightBuilder highlightBuilder = new HighlightBuilder();
         * highlightBuilder.preTags("<span style=\"color:red\">");
         * highlightBuilder.postTags("</span>");
         * highlightBuilder.field("name");
         * highlightBuilder.field("age");
         * searchSourceBuilder.highlighter(highlightBuilder);
         * 
         * // 加入聚合,分组
         * TermsAggregationBuilder aggregation = AggregationBuilders.terms("by_company").field("company");
         * // 年纪最大
         * aggregation.subAggregation(AggregationBuilders.max("max_age").field("age"));
         * searchSourceBuilder.aggregation(aggregation);
         * 
         * // 做查询建议
         * SuggestionBuilder termSuggestionBuilder = SuggestBuilders.termSuggestion("user").text("kmichy");
         * SuggestBuilder suggestBuilder = new SuggestBuilder();
         * suggestBuilder.addSuggestion("suggest_user", termSuggestionBuilder);
         * searchSourceBuilder.suggest(suggestBuilder);
         */
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(searchSourceBuilder.query(queryBuilder));
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHit[] searchHits = searchResponse.getHits().getHits();
        for (SearchHit hit : searchHits) {
            System.out.println("--------结果:--------->" + hit.getSourceAsString());
        }

    }
}
