package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criterial {

	private int pageNum; // 페이지
	private int amount; // 페이지 개수
	
	private String type; // 검색 조건, 제목, 내용, 작성자
	private String keyword; // 찾을 값(예를 들어 수정, 쪼꼼 등)
	
	public Criterial() {
		this(1, 10); // 1~10번째 목록 보여주기
	}

	public Criterial(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	// getTypeArr() : 내용, 제목, 작성자 2건 이상 조회
	// String[] {} : 배열
	public String[] getTypeArr() {
		return type==null ? new String[] {} : type.split("");
	}

}
