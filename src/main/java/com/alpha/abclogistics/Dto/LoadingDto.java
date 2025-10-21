package com.alpha.abclogistics.Dto;

public class LoadingDto {
	private String Date;
	private String Time;
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public LoadingDto(String date, String time) {
		super();
		Date = date;
		Time = time;
	}
	public LoadingDto() {
		super();
	}
	

}
