package com.blubank.interviewpostquestion.service.impl;

import com.blubank.interviewpostquestion.entity.ABaseEntity;
import com.blubank.interviewpostquestion.entity.HashtagEntity;
import com.blubank.interviewpostquestion.repository.HashtagRepository;
import com.blubank.interviewpostquestion.service.HashtagService;
import com.blubank.interviewpostquestion.service.api.hashtag.HashtagModel;
import com.blubank.interviewpostquestion.service.api.hashtag.HashtagRankModel;
import com.blubank.interviewpostquestion.util.Clock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class HashtagServiceImpl implements HashtagService {

    private final int TOP_TRENDS_RESULT_LIMIT = 10;

    private final int TOP_TRENDS_RECENT_DAYS = 7;

    private final Pattern HASHTAG_PATTERN = Pattern.compile("#\\w+");

    @Autowired
    private HashtagRepository hashtagRepository;

    @Autowired
    private Clock clock;


    @Override
    public HashtagModel findByHashtag(String hashtag) {
        Optional<HashtagEntity> optionalHashtagEntity = hashtagRepository.findByHashtag(hashtag.toLowerCase(Locale.ROOT));
        if (optionalHashtagEntity.isEmpty())
            return null;

        HashtagEntity hashtagEntity = optionalHashtagEntity.get();

        return HashtagModel.builder()
                .hashtag(hashtagEntity.getHashtag())
                .tweetIds(hashtagEntity.getTweets().stream().map(ABaseEntity::getId).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public List<HashtagRankModel> topTrends(int resultLimit, int recentDays) {
        Timestamp recentDaysTimestamp = clock.getRecentDaysTimestamp(recentDays);

        return hashtagRepository.findTopTrends(resultLimit, recentDaysTimestamp)
                .stream()
                .map(item -> new HashtagRankModel(item.getHashtag(), item.getCount()))
                .toList();
    }

    @Override
    public List<HashtagRankModel> topTrends() {
        Timestamp recentDaysTimestamp = clock.getRecentDaysTimestamp(TOP_TRENDS_RECENT_DAYS);

        return hashtagRepository.findTopTrends(TOP_TRENDS_RESULT_LIMIT, recentDaysTimestamp)
                .stream()
                .map(item -> new HashtagRankModel(item.getHashtag(), item.getCount()))
                .toList();
    }

    @Override
    public Set<HashtagEntity> saveFromContent(String content) {
        Set<String> hashtagSet = extractHastags(content);
        if (hashtagSet.size() == 0)
            return null;

        Set<HashtagEntity> hashtagEntitySet = new HashSet<>();
        for (String hashtag : hashtagSet) {
            Optional<HashtagEntity> optionalHashtagEntity = hashtagRepository.findByHashtag(hashtag.toLowerCase(Locale.ROOT));
            if (optionalHashtagEntity.isPresent()) {
                hashtagEntitySet.add(optionalHashtagEntity.get());
            } else {
                HashtagEntity hashtagEntity = new HashtagEntity();
                hashtagEntity.setHashtag(hashtag.toLowerCase(Locale.ROOT));
                hashtagEntitySet.add(hashtagEntity);
                hashtagRepository.save(hashtagEntity);
            }
        }

        return hashtagEntitySet;
    }


    private Set<String> extractHastags(String content) {
        Matcher matcher = HASHTAG_PATTERN.matcher(content);
        Set<String> hashtagSet = new HashSet<>();
        while (matcher.find()) {
            hashtagSet.add(matcher.group(0));
        }
        return hashtagSet;
    }

}