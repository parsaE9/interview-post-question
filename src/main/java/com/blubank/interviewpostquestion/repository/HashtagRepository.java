package com.blubank.interviewpostquestion.repository;

import com.blubank.interviewpostquestion.entity.HashtagEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HashtagRepository extends CrudRepository<HashtagEntity, Long> {

    Optional<HashtagEntity> findByHashtag(String hashtag);

}