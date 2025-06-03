package org.zerock.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReplyPageDTO { // 댓글 페이징 응답을 담당하는 DTO
	
	private int replyCnt; // 댓글 개수 
	private List<ReplyVO> list; // 댓글 전체 정보

}
