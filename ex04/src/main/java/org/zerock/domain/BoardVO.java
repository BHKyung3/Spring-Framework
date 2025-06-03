package org.zerock.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 	bno number(10, 0),
    title varchar2(200) not null,
    content varchar2(2000) not null,
    writer varchar2(50) not null,
    regdate date default sysdate, --작성일
    updatedate date default sysdate -- 수정일
 */

@Data // setter, getter, ToString 사용을 위해 기재
@Builder // 객체를 생성할 때 Builder 패턴 사용 가능
@NoArgsConstructor // 매개변수가 없는 생성자 자동 생성
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자 자동 생성
public class BoardVO {
	
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updatedate;
	
	private int replyCnt;
}
