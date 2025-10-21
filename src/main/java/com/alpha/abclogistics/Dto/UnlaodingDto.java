package com.alpha.abclogistics.Dto;


public class UnlaodingDto {
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
@Override
public String toString() {
	return "UnlaodingDto [Date=" + Date + ", Time=" + Time + "]";
}
public UnlaodingDto() {
	super();
}
public UnlaodingDto(String date, String time) {
	super();
	Date = date;
	Time = time;
}
public void setTime(String time) {
	Time = time;
}
}
