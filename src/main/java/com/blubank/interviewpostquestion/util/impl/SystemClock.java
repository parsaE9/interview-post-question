package com.blubank.interviewpostquestion.util.impl;

import com.blubank.interviewpostquestion.util.Clock;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Component
public class SystemClock implements Clock {
	@Override
	public long currentTime() {
		return System.currentTimeMillis();
	}


	@Override
	public Timestamp getRecentDaysTimestamp(int recentDays) {
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_YEAR, -recentDays);
		long yesterdayMillis = cal.getTime().getTime();
		return new Timestamp(yesterdayMillis);
	}

}