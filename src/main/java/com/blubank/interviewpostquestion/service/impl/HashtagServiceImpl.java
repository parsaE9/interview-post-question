package com.blubank.interviewpostquestion.service.impl;

import com.blubank.interviewpostquestion.entity.ABaseEntity;
import com.blubank.interviewpostquestion.entity.HashtagEntity;
import com.blubank.interviewpostquestion.repository.HashtagRepository;
import com.blubank.interviewpostquestion.service.HashtagService;
import com.blubank.interviewpostquestion.service.api.hashtag.HashtagModel;
import com.blubank.interviewpostquestion.service.api.hashtag.HashtagRankModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class HashtagServiceImpl implements HashtagService {

    private final Pattern HASHTAG_PATTERN = Pattern.compile("#\\w+");

    @Autowired
    private HashtagRepository hashtagRepository;

    @Override
    public HashtagModel findByHashtag(String hashtag) {
        Optional<HashtagEntity> optionalHashtagEntity = hashtagRepository.findByHashtag(hashtag);
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
        return null;
    }

    @Override
    public Set<HashtagEntity> saveFromContent(String content) {
        Set<String> hashtagSet = extractHastags(content);
        if (hashtagSet.size() == 0)
            return null;

        Set<HashtagEntity> hashtagEntitySet = new HashSet<>();
        for (String hashtag : hashtagSet) {
            HashtagEntity hashtagEntity = new HashtagEntity();
            hashtagEntity.setHashtag(hashtag);
            hashtagEntitySet.add(hashtagEntity);
        }

        List<HashtagEntity> hashtagEntityList = (List<HashtagEntity>) hashtagRepository.saveAll(hashtagEntitySet);
        return new HashSet<>(hashtagEntityList);
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