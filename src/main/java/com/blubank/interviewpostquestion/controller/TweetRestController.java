package com.blubank.interviewpostquestion.controller;

import com.blubank.interviewpostquestion.controller.api.tweet.TweetLikeRequest;
import com.blubank.interviewpostquestion.controller.api.tweet.TweetLikeResponse;
import com.blubank.interviewpostquestion.controller.api.tweet.TweetSaveRequest;
import com.blubank.interviewpostquestion.controller.api.tweet.TweetSaveResponse;
import com.blubank.interviewpostquestion.service.TweetService;
import com.blubank.interviewpostquestion.service.api.tweet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tweets", produces = "application/json")
public class TweetRestController {

    @Autowired
    private TweetService tweetService;


    @PostMapping
    public TweetSaveResponse saveTweet(@RequestBody TweetSaveRequest request) {
        TweetSaveParam tweetSaveParam = TweetSaveParam.builder()
                .author(request.getAuthor())
                .content(request.getContent())
                .build();

        TweetSaveResult tweetSaveResult = tweetService.save(tweetSaveParam);

        return TweetSaveResponse.builder().tweetId(tweetSaveResult.getTweetId()).build();
    }


    @GetMapping(value = "/{id}")
    public TweetModel loadTweet(@PathVariable Long id, @RequestParam Boolean includeHashtags, @RequestParam Boolean includeLikeCount) {
        TweetLoadParam tweetLoadParam = TweetLoadParam.builder()
                .tweetId(id)
                .includeHashtags(includeHashtags)
                .includeLikeCount(includeLikeCount)
                .build();

        return tweetService.load(tweetLoadParam);
    }


    @PostMapping(value = "/{id}/like")
    public TweetLikeResponse likeTweet(@PathVariable Long id, @RequestBody TweetLikeRequest request) {
        TweetLikeParam tweetLikeParam = TweetLikeParam.builder()
                .tweetId(id)
                .likedBy(request.getLikedBy())
                .build();

        TweetLikeResult tweetLikeResult = tweetService.like(tweetLikeParam);

        return TweetLikeResponse.builder().success(tweetLikeResult.isSuccess()).build();
    }


}