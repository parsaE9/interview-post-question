package com.blubank.interviewpostquestion.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class TweetEntity extends ABaseEntity {

    private String author;

    private String content;

    private Set<String> likes = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "tweets_hashtags",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_id")
    )
    private Set<HashtagEntity> hashtags = new HashSet<>();


    public void addLike(String like) {
        likes.add(like);
    }

    public void addHashtag(HashtagEntity hashtag) {
        hashtags.add(hashtag);
    }

}