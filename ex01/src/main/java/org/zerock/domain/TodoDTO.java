package org.zerock.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO {
	
	private String title;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd") // 원하는 형식으로 날짜를 기입 받을 때 사용
	private Date dueDate;
}
