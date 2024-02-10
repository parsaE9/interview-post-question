package com.blubank.interviewpostquestion.util;

import java.sql.Timestamp;

public interface Clock {

	long currentTime();

	Timestamp getRecentDaysTimestamp(int recentDays);

}