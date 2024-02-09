package com.blubank.interviewpostquestion.repository;

import com.blubank.interviewpostquestion.entity.TweetEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends CrudRepository<TweetEntity, Long> {

}