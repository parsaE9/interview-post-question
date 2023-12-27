package com.blubank.interviewpostquestion.service.api.tweet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TweetLikeParam {

	private long tweetId;
	private String likedBy;

}
