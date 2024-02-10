package com.blubank.interviewpostquestion;

import com.blubank.interviewpostquestion.entity.HashtagEntity;
import com.blubank.interviewpostquestion.entity.TweetEntity;
import com.blubank.interviewpostquestion.repository.HashtagRepository;
import com.blubank.interviewpostquestion.repository.TweetRepository;
import com.blubank.interviewpostquestion.service.HashtagService;
import com.blubank.interviewpostquestion.service.TweetService;
import com.blubank.interviewpostquestion.service.api.hashtag.HashtagModel;
import com.blubank.interviewpostquestion.service.api.hashtag.HashtagRankModel;
import com.blubank.interviewpostquestion.service.api.tweet.TweetSaveParam;
import com.blubank.interviewpostquestion.service.api.tweet.TweetSaveResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class TwitterApplication implements CommandLineRunner {

	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private HashtagRepository hashtagRepository;

	@Autowired
	private TweetService tweetService;

	@Autowired
	private HashtagService hashtagService;


	public static void main(String[] args) {
		SpringApplication.run(TwitterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		saveTweet("Reza", "#developer", "#blubank", "#contest");
//		saveTweet("Ahmad", "#programming", "#blubank", "#contest");
//		saveTweet("Maryam", "#article", "#blubank");
//
//		// We have 5 distinct hashtags, but the limit is 2
//		List<HashtagRankModel> ranks = hashtagService.topTrends(2, 5);
//		System.out.println(ranks);

//		TweetSaveResult result1 = saveTweet("Reza", "#Blubank", "#blubank", "#contest");
//		TweetSaveResult result2 = saveTweet("Ahmad", "#programming", "#blubank");
//
//		HashtagModel model = hashtagService.findByHashtag("#BLUBANK");
//		if (model.getTweetIds().size() == 2);
//			System.out.println("yes");




//		HashtagEntity h1 = new HashtagEntity();
//		h1.setHashtag("game");
//		hashtagRepository.save(h1);
//
//		TweetEntity t1 = new TweetEntity();
//		t1.setAuthor("parsa");
//		t1.addHashtag(h1);
//		tweetRepository.save(t1);
//
//		HashtagEntity h2 = hashtagRepository.findByHashtag("game").get();
//		System.out.println(h2);


	}

	private TweetSaveResult saveTweet(String author, String... hashtags) {
		return tweetService.save(TweetSaveParam.builder()
				.author(author)
				.content("Hello " + String.join(" ", hashtags))
				.build());
	}


}