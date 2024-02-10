package com.blubank.interviewpostquestion.util;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Setter
@AllArgsConstructor
public class MagicClock implements Clock {

	private long currentTime;

	@Override
	public long currentTime() {
		return currentTime;
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