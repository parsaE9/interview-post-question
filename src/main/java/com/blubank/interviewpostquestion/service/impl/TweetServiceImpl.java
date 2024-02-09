package com.blubank.interviewpostquestion.service.impl;

import com.blubank.interviewpostquestion.entity.HashtagEntity;
import com.blubank.interviewpostquestion.entity.TweetEntity;
import com.blubank.interviewpostquestion.repository.TweetRepository;
import com.blubank.interviewpostquestion.service.HashtagService;
import com.blubank.interviewpostquestion.service.TweetService;
import com.blubank.interviewpostquestion.service.api.tweet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private HashtagService hashtagService;

    @Autowired
    private TweetRepository tweetRepository;


    @Override
    @Transactional
    public TweetSaveResult save(TweetSaveParam param) {
        TweetEntity tweetEntity = new TweetEntity();
        tweetEntity.setAuthor(param.getAuthor());
        tweetEntity.setContent(param.getContent());
        tweetEntity.setHashtags(hashtagService.saveFromContent(param.getContent()));
        tweetRepository.save(tweetEntity);

        return TweetSaveResult.builder().tweetId(tweetEntity.getId()).build();
    }


    @Override
    public TweetLikeResult like(TweetLikeParam param) {
        TweetLikeResult tweetLikeResult = TweetLikeResult.builder()
                .success(false)
                .build();

        Optional<TweetEntity> optionalTweetEntity = tweetRepository.findById(param.getTweetId());
        if (optionalTweetEntity.isEmpty())
            return tweetLikeResult;

        TweetEntity tweetEntity = optionalTweetEntity.get();
        if (!tweetEntity.getLikes().contains(param.getLikedBy())) {
            tweetEntity.addLike(param.getLikedBy());
            tweetRepository.save(tweetEntity);
            tweetLikeResult.setSuccess(true);
        }

        return tweetLikeResult;
    }


    @Override
    public TweetModel load(TweetLoadParam param) {
        Optional<TweetEntity> optionalTweetEntity = tweetRepository.findById(param.getTweetId());
        if (optionalTweetEntity.isEmpty())
            return null;

        TweetEntity tweetEntity = optionalTweetEntity.get();
        TweetModel tweetModel = TweetModel.builder()
                .tweetId(tweetEntity.getId())
                .author(tweetEntity.getAuthor())
                .content(tweetEntity.getContent())
                .time(tweetEntity.getCreationDate().getTime())
                .build();

        if (param.isIncludeLikeCount())
            tweetModel.setLikeCount(tweetEntity.getLikes().size());
        else
            tweetModel.setLikeCount(0);

        if (param.isIncludeHashtags())
            tweetModel.setHashtags(tweetEntity.getHashtags().stream().map(HashtagEntity::getHashtag).collect(Collectors.toSet()));
        else
            tweetModel.setHashtags(new HashSet<>());

        return tweetModel;
    }

}