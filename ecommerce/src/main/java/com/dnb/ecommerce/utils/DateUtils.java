package com.dnb.ecommerce.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class DateUtils {

	public String getCurrentDate(String dateFormat) {
		LocalDate dateObj = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		String date = dateObj.format(formatter);

		return date;
	}
	
	public LocalDate getLocalDate(String dateStr, String dateFormat) {
		LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(dateFormat));
		return date;
	}
}
