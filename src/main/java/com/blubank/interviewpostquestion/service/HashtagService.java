package com.blubank.interviewpostquestion.service;

import com.blubank.interviewpostquestion.entity.HashtagEntity;
import com.blubank.interviewpostquestion.service.api.hashtag.HashtagModel;
import com.blubank.interviewpostquestion.service.api.hashtag.HashtagRankModel;

import java.util.List;
import java.util.Set;

public interface HashtagService {

    HashtagModel findByHashtag(String hashtag);

    List<HashtagRankModel> topTrends(int resultLimit, int recentDays);

    List<HashtagRankModel> topTrends();

    Set<HashtagEntity> saveFromContent(String content);

}