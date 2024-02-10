package com.blubank.interviewpostquestion.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class HashtagEntity extends ABaseEntity {

    @Column(unique = true)
    private String hashtag;

    @ManyToMany(mappedBy = "hashtags", fetch = FetchType.EAGER)
    private Set<TweetEntity> tweets = new HashSet<>();


    public void addTweet(TweetEntity tweet) {
        tweets.add(tweet);
    }

}