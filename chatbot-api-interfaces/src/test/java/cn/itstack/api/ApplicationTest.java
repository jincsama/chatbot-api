package cn.itstack.api;

import junit.framework.TestCase;
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
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class ApplicationTest
    extends TestCase
{
    @Test
    public  void  reptile(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20");
        get.addHeader("cookie","zsxq_access_token=4E47921B-9EBF-5DC9-40CB-3703A47F6B5F_263930FCC58E7904; zsxqsessionid=d01d4e66cea9d7426e1c8e4a4a0906fe; abtest_env=product; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2218df85a644d8e2-01c57ee9508eb6c-4c657b58-1327104-18df85a644e11f0%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThkZjg1YTY0NGQ4ZTItMDFjNTdlZTk1MDhlYjZjLTRjNjU3YjU4LTEzMjcxMDQtMThkZjg1YTY0NGUxMWYwIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%2218df85a644d8e2-01c57ee9508eb6c-4c657b58-1327104-18df85a644e11f0%22%7D; sajssdk_2015_cross_new_user=1");
        get.addHeader("content-Type","application/json, text/plain, */*");

        try {
            CloseableHttpResponse response = httpClient.execute(get);
            if (response.getCode() == HttpStatus.SC_OK) {
                System.out.println(EntityUtils.toString(response.getEntity()));
            }else {
                System.out.println(response.getCode());
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public  void answer(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/188852844814152/comments");
        post.addHeader("cookie","zsxq_access_token=4E47921B-9EBF-5DC9-40CB-3703A47F6B5F_263930FCC58E7904; zsxqsessionid=d01d4e66cea9d7426e1c8e4a4a0906fe; abtest_env=product; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2218df85a644d8e2-01c57ee9508eb6c-4c657b58-1327104-18df85a644e11f0%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThkZjg1YTY0NGQ4ZTItMDFjNTdlZTk1MDhlYjZjLTRjNjU3YjU4LTEzMjcxMDQtMThkZjg1YTY0NGUxMWYwIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%2218df85a644d8e2-01c57ee9508eb6c-4c657b58-1327104-18df85a644e11f0%22%7D; sajssdk_2015_cross_new_user=1");
        post.addHeader("content-Type","application/json, text/plain, */*");

        String paramJson ="{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"测试\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"mentioned_user_ids\": []\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("test/json","UTF-8"));
        post.setEntity(stringEntity);
        try {
            CloseableHttpResponse response = httpClient.execute(post);
            if (response.getCode() == HttpStatus.SC_OK) {
                System.out.println(EntityUtils.toString(response.getEntity()));
            }else {
                System.out.println(response.getCode());
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
