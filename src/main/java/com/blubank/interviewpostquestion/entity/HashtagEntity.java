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

    @ManyToMany
    @JoinTable(
            name = "hashtags_tweets",
            joinColumns = @JoinColumn(name = "hashtag_id"),
            inverseJoinColumns = @JoinColumn(name = "tweet_id")
    )
    private Set<TweetEntity> tweets = new HashSet<>();


    public void addTweet(TweetEntity tweet) {
        tweets.add(tweet);
    }

}