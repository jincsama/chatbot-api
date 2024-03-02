package cn.itstack.api.domain.zsxq.service;

import cn.itstack.api.domain.zsxq.model.aggregs.UnAnsweredQuestionsAggregates;
import cn.itstack.api.domain.zsxq.model.req.AnswerReq;
import cn.itstack.api.domain.zsxq.model.req.ReqData;
import cn.itstack.api.domain.zsxq.model.res.AnswerPes;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.itstack.api.domain.zsxq.IZsxqApi;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ZsxqApi implements IZsxqApi {

    private Logger logger = LoggerFactory.getLogger(ZsxqApi.class);

    @Override
    public UnAnsweredQuestionsAggregates queryUnAnsweredQuestionTopicId(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/" + groupId + "/topics?scope=all&count=20");
        get.addHeader("cookie", cookie);
        get.addHeader("content-Type", "application/json, text/plain, */*");
        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getCode() == HttpStatus.SC_OK) {
            String jsonStr = null;
            try {
                jsonStr = EntityUtils.toString(response.getEntity());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return JSON.parseObject(jsonStr, UnAnsweredQuestionsAggregates.class);
        } else {
            throw new RuntimeException("queryUnAnsweredQuestionsTopicId Err Code is " + response.getCode());
        }
    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/" + topicId + "/comments");
        post.addHeader("cookie", cookie);
        post.addHeader("content-Type", "application/json, text/plain, */*");
        post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36 Edg/122.0.0.0");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"text测试\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"mentioned_user_ids\": []\n" +
                "  }\n" +
                "}";


//        AnswerReq answerReq = new AnswerReq(new ReqData(text, silenced));
//            String paramJson = new Gson().toJson(answerReq);

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("test/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getCode() == HttpStatus.SC_OK) {
            String jsonStr = null;
            try {
                jsonStr = EntityUtils.toString(response.getEntity());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            AnswerPes answerPes = JSONObject.parseObject(jsonStr, AnswerPes.class);
            logger.info("回复结果。groupId:{} jsonStr:{}", groupId, jsonStr);
            return answerPes.isSucceeded();
        } else {
            throw new RuntimeException("answer Err Code is " + response.getCode());
        }
    }
}
