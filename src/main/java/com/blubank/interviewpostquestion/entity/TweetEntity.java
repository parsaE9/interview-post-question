package com.blubank.interviewpostquestion.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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

    @ManyToMany(mappedBy = "tweets", fetch = FetchType.EAGER)
    private Set<HashtagEntity> hashtags = new HashSet<>();


    public void addLike(String like) {
        likes.add(like);
    }

    public void addHashtag(HashtagEntity hashtag) {
        hashtags.add(hashtag);
    }

}