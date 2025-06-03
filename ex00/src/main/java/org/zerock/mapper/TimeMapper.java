package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.dto.BoardVO;

public interface TimeMapper {
	
	@Select("select sysdate from dual")
	public String getTime();
	
	public String getTime2();
	
	public List<BoardVO> selectAllList(); // 전체 데이타 가져오기(select 구문으로)
	
	public BoardVO selectOneByNum(int num); // 단건 데이터 조회
	
	public void insertBoard(BoardVO vo); // 데이터 추가

}
