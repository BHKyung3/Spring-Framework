
// Mapper 인터페이스로 만들고 + .xml 파일 필수 생성

package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criterial;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
	
	public int insert(ReplyVO vo); // DB에 댓글 9개 생성
	   
	public ReplyVO read(Long rno); // 특정 댓글 읽기
	   
	public int delete(Long rno); // 특정 댓글 삭제
	    
	public int update(ReplyVO vo); // 특정 댓글 수정
	
	public List<ReplyVO> getListWithPaging(
			@Param("cri") Criterial cri,
			@Param("bno") Long bno
			);
	
	public int getCountByBno(Long rno); // 댓글 숫자 파악
}
