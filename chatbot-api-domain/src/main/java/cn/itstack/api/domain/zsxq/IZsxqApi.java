package cn.itstack.api.domain.zsxq;

import cn.itstack.api.domain.zsxq.model.aggregs.UnAnsweredQuestionsAggregates;

import java.io.IOException;

public interface IZsxqApi {


    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionTopicId(String groupId,String cookie) throws IOException;

    boolean answer(String groupId,String cookie,String topicId,String text,boolean silenced) throws IOException;
}
