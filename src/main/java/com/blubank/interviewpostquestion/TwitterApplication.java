package com.blubank.interviewpostquestion;

import com.blubank.interviewpostquestion.entity.HashtagEntity;
import com.blubank.interviewpostquestion.entity.TweetEntity;
import com.blubank.interviewpostquestion.repository.HashtagRepository;
import com.blubank.interviewpostquestion.repository.TweetRepository;
import com.blubank.interviewpostquestion.service.TweetService;
import com.blubank.interviewpostquestion.service.api.tweet.TweetSaveParam;
import com.blubank.interviewpostquestion.service.api.tweet.TweetSaveResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class TwitterApplication implements CommandLineRunner {

	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private HashtagRepository hashtagRepository;

	@Autowired
	private TweetService tweetService;


	public static void main(String[] args) {
		SpringApplication.run(TwitterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		TweetSaveResult result1 = saveTweet("Reza", "#Blubank", "#blubank", "#contest");
//		TweetSaveResult result2 = saveTweet("Ahmad", "#programming", "#blubank");


	}

	private TweetSaveResult saveTweet(String author, String... hashtags) {
		return tweetService.save(TweetSaveParam.builder()
				.author(author)
				.content("Hello " + String.join(" ", hashtags))
				.build());
	}


}