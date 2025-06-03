
// DB 연결

package org.zerock.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
  	create table tbl_reply(
    rno number(10, 0),
    bno number(10, 0) not null, -- 외래키 설정
    reply VARCHAR2(1000) not null,
    replyer VARCHAR2(50) not null,
    replyDate date DEFAULT sysdate,
    updateDate date DEFAULT sysdate
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyVO {
	
	private Long rno; // 댓글 번호
	private Long bno; // 게시글 번호
	
	private String reply; // 댓글 내용
	private String replyer; // 댓글 작성자
	
	private Date replyDate; // 댓글이 처음 작성된 날짜 / 시간
	private Date updateDate; // 댓글이 마지막으로 수정된 날짜 / 시간

}
