package com.zzz.elasticsearch.start.service;

import com.alibaba.fastjson2.JSON;
import com.zzz.elasticsearch.start.domain.Content;
import com.zzz.elasticsearch.start.utils.HtmlParseUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description ContentService
 * @Author 张卫刚
 * @Date Created on 2023/8/31
 */
@Service
public class ContentService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    public static void main(String[] args) throws Exception {
        new ContentService().parseContent("java");
    }

    public Boolean parseContent (String keywords) throws Exception {
        List<Content> contents = new HtmlParseUtil().parseJD(keywords);
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");
        for (int i=0;i < contents.size();++i) {
            bulkRequest.add(
                    new IndexRequest("test1")
                            .source(JSON.toJSONString(contents.get(i)), XContentType.JSON));
        }
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return !bulkResponse.hasFailures();
    }

    public List<Map<String,Object>> searchPagehighLight   (String keyword, int pageNo, int pageSize) throws IOException {
        if (pageNo <= 1)
            pageNo = 1;

        SearchRequest searchRequest = new SearchRequest("test1");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(pageNo);
        builder.size(pageSize);

        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title",keyword);
        builder.query(termQueryBuilder);
        builder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.requireFieldMatch(false);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        builder.highlighter(highlightBuilder);

        searchRequest.source(builder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        ArrayList<Map<String,Object>> list= new ArrayList<>();
        for (SearchHit hit: searchResponse.getHits().getHits()) {

            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title = highlightFields.get("title");
            Map<String,Object> sourceAsMap = hit.getSourceAsMap();

            if (title != null) {
                Text[] fragments = title.fragments();
                StringBuilder nTitle = new StringBuilder();
                for (Text text:fragments) {
                    nTitle.append(text);
                }
                sourceAsMap.put("title",nTitle);
            }
            list.add(hit.getSourceAsMap());
        }
        return list;
    }

    public List<Map<String,Object>> searchPage (String keyword, int pageNo,int pageSize) throws IOException {
        if (pageNo <= 1) {
            pageNo = 1;
        }

        SearchRequest searchRequest = new SearchRequest("test1");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(pageNo);
        builder.size(pageSize);

        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title",keyword);
        builder.query(termQueryBuilder);
        builder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(builder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        ArrayList<Map<String,Object>> list= new ArrayList<>();
        for (SearchHit hit: searchResponse.getHits().getHits()) {
            list.add(hit.getSourceAsMap());
        }
        return list;
    }
}
