package cn.itstack.api;

import cn.itstack.api.domain.zsxq.IZsxqApi;
import cn.itstack.api.domain.zsxq.model.aggregs.UnAnsweredQuestionsAggregates;
import cn.itstack.api.domain.zsxq.model.res.RespData;
import cn.itstack.api.domain.zsxq.model.vo.showComments;
import cn.itstack.api.domain.zsxq.model.vo.Topics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunText {
    private Logger logger = LoggerFactory.getLogger(SpringBootRunText.class);
    @Value("${chat-api.groupId}")
    private String groupId;
    @Value("${chat-api.cookie}")
    private String cookie;
    @Resource
    private IZsxqApi zsxqApi;

    @Test
    public void text_ZxsqApi() throws IOException {
        UnAnsweredQuestionsAggregates aggregates = zsxqApi.queryUnAnsweredQuestionTopicId(groupId, cookie);

        RespData respData = aggregates.getResp_data();
        List<Topics> topics = respData.getTopics();
        for (Topics topic : topics) {
            String topicId = String.valueOf(topic.getTopic_id());
            String text = topic.getTalk().getText();
            String targetString = "<e type=\"mention\" uid=\"212841525455281\" title=\"%40%E3%82%BD%E3%83%A9\" />";
            int startIndex = text.indexOf(targetString);
            int endIndex = startIndex + targetString.length();
            if (startIndex != -1) {
                // 输出除目标字符串外的内容
                String output = text.substring(0, startIndex) + text.substring(endIndex);
                System.out.println(aggregates);
                zsxqApi.answer(groupId,cookie,topicId,text,false);
            }
        }
    }
}