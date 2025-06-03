package org.zerock.dto;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data; // 	Getter/Setter, toString, equals 등을 자동으로 만들어준다

@Data // Lombok 기능 적용, Getter/Setter, toString(), equals(), hashCode() 자동 생성
public class BoardVO {
	
	private int num;
	private String pass;
	private String name;
	private String email;
	private String title;
	private String content;
	private int readCount;        //readcount
	private Timestamp writeDate;  //writedate
}
