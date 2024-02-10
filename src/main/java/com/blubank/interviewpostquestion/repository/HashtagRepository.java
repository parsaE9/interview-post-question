package com.blubank.interviewpostquestion.repository;

import com.blubank.interviewpostquestion.entity.HashtagEntity;
import com.blubank.interviewpostquestion.repository.api.HashtagRankModelInterface;
import com.blubank.interviewpostquestion.service.api.hashtag.HashtagRankModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface HashtagRepository extends CrudRepository<HashtagEntity, Long> {

    Optional<HashtagEntity> findByHashtag(String hashtag);


    @Query(value = "SELECT he.hashtag, count(he.hashtag) as count " +
            "FROM hashtag_entity he " +
            "JOIN tweets_hashtags th ON he.id = th.hashtag_id " +
            "WHERE he.creation_date > :paramData " +
            "GROUP BY he.hashtag " +
            "ORDER BY count DESC " +
            "LIMIT :resultLimit "
            , nativeQuery = true)
    List<HashtagRankModelInterface> findTopTrends(@Param("resultLimit") int resultLimit, @Param("paramData") Timestamp paramData);

}


//    SELECT a.hashtag,count(a.hashtag)
//
//        FROM HASHTAG_ENTITY a join TWEETS_HASHTAGS b on a.id=b.hashtag_id
//        where a.id< 10
//
//        group by a.hashtag
//        order by count(a.hashtag)desc
//        limit 2
