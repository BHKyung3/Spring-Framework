package org.zerock.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;

public interface BoardMapper {
	// 전체 게시글 목록 조회
	public List<BoardVO> getList();
	// 특정 게시글 단건 조회
	public BoardVO read(Long bno);
	// 데이터 추가
	public void insert(BoardVO board);
	// 데이터 추가 + bno 번호 확인
	public void insertSelectKey(BoardVO board);
	// 데이터 삭제
	int delete(Long bno);
	// 데이터 수정
	public int update(BoardVO board);
	// 페이지 리스트(페이징처리)
	public List<BoardVO> getListWithPaging(Criterial cri);
	// DB 전체 데이터 가져오기
	public int getTotal(Criterial cri);
	// 조건 검색 여부
	public List<BoardVO> searchTest(Map<String, Map<String, String>> map);
	// 댓글 증가, 감소 메소드(2개 이상 작성 시 @Param 기재 필요)
	public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
}
