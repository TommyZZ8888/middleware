package com.zzz.elasticsearch;

import com.alibaba.fastjson2.JSON;
import com.zzz.elasticsearch.start.domain.User;
import com.zzz.elasticsearch.start.service.EsService;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexInformation;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@SpringBootTest
class ElasticsearchApplicationTests {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private EsService esService;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    @Test
    public void test() throws IOException {
        esService.insert();
    }

    @Test
    void contextLoads() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest("user");
        boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    void testQuery(){
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setSource(JSON.toJSONString(new User("用户1",1)));
        String indexOperations = elasticsearchRestTemplate.doIndex(indexQuery,IndexCoordinates.of("user"));
        System.out.println(indexOperations);
    }

    @Test
    void testQuery2() throws IOException {
        ActionListener<GetResponse> listener = new ActionListener<GetResponse>() {
            @Override
            public void onResponse(GetResponse getResponse) {
                String index = getResponse.getIndex();
                System.out.println(index);
            }

            @Override
            public void onFailure(Exception e) {

            }
        };
        GetRequest getRequest = new GetRequest("user","1");
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(response.getIndex()));
    }

    @Test
    void testDelete() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest("user");
        if (client.indices().exists(getIndexRequest, RequestOptions.DEFAULT)) {
            DeleteIndexRequest request = new DeleteIndexRequest("user");
            System.out.println(client.indices().delete(request, RequestOptions.DEFAULT));
        }
        GetIndexRequest getIndexRequest2 = new GetIndexRequest("user");
        System.out.println(client.indices().exists(getIndexRequest, RequestOptions.DEFAULT));
    }




    @Test
    void testAddDocument() throws IOException {
        User user = new User("狂神说java", 3);
        IndexRequest request = new IndexRequest("user");

        request.id("1");
        IndexRequest source = request.source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse response = client.index(source, RequestOptions.DEFAULT);
        System.out.println(response.toString());
        System.out.println(response.status());
    }

    @Test
   void testAddDocument1(){
       String result = elasticsearchRestTemplate.delete("33aZSooBtN_6yOgn2d52", IndexCoordinates.of("user"));
       System.out.println(result);
   }


    @Test
    void testUpdateDocument() throws IOException {
        User user = new User("张三", 8);
        UpdateRequest request = new UpdateRequest("user", "1");
        UpdateRequest updateRequest = request.doc(JSON.toJSONString(user), XContentType.JSON);
        UpdateResponse response = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(response.status());
    }

    @Test
    void testBulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        List<User> list = new ArrayList<>();
        IntStream.rangeClosed(1, 10).forEach(item -> {
            list.add(new User("用户" + item, item));
        });
        for (int i = 0; i < list.size(); i++) {
            IndexRequest request = new IndexRequest("user")
                    .id(String.valueOf(i+1))
                    .source(JSON.toJSONString(list.get(i)),XContentType.JSON);
            bulkRequest.add(request);
        }
        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.status());
        bulk.forEach(System.out::println);
    }


	@Test
	void testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermQueryBuilder termQuery = QueryBuilders.termQuery("name", "用户");
//        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "用户");
        searchSourceBuilder.query(termQuery);
        searchRequest.source(searchSourceBuilder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response.status());
        System.out.println(JSON.toJSONString(response.getHits()));
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }


    /**/////////////////////////////// elasticsearchOperations ///////////////////////////////////////////*/
    @Test
     void insert() throws IOException {
        IndexCoordinates indexCoordinates =  IndexCoordinates.of("user");
        boolean exists = elasticsearchOperations.exists("sAOLRZEBPeo7fepiYs37", indexCoordinates);
        System.out.println(exists);
        User user = new User("zhangsan", 22);
        User save = elasticsearchOperations.save(user);
        System.out.println(save.getAge());
    }

    @Test
     void query() throws IOException {
        User user = elasticsearchOperations.get("sAOLRZEBPeo7fepiYs37", User.class);
        System.out.println(user.getAge());

        Criteria criteria = new Criteria("age").is(1);
        Query query = new CriteriaQuery(criteria);
        org.springframework.data.elasticsearch.core.SearchHit<User> userSearchHit = elasticsearchOperations.searchOne(query, User.class);
        User content = userSearchHit.getContent();
        System.out.println(content.getAge());
    }

}
