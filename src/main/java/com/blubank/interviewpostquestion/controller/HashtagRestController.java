package com.blubank.interviewpostquestion.controller;

import com.blubank.interviewpostquestion.controller.api.hashtag.HashtagTrendResponse;
import com.blubank.interviewpostquestion.service.HashtagService;
import com.blubank.interviewpostquestion.service.api.hashtag.HashtagModel;
import com.blubank.interviewpostquestion.service.api.hashtag.HashtagRankModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/hashtags", produces = "application/json")
public class HashtagRestController {

    @Autowired
    private HashtagService hashtagService;


    @GetMapping(value = "/{id}")
    public HashtagModel loadHashtag(@PathVariable("id") String hashtag) {
        return hashtagService.findByHashtag(hashtag.toLowerCase(Locale.ROOT));
    }

    @GetMapping(value = "/top-trends")
    public HashtagTrendResponse loadTopTrends() {
        return HashtagTrendResponse.builder()
                .items(hashtagService.topTrends())
                .build();
    }


}