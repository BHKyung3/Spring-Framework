package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;
import org.zerock.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor // 생성자 주입
public class BoardServiceImpl implements BoardService{
	
	// @RequiredArgsConstructor + final 조합되어 객체가 주입됨
	private final BoardMapper mapper;
	
	// 데이터 등록
	@Override
	public void register(BoardVO board) {
		log.info("register.........." + board);
		mapper.insertSelectKey(board);
		
	}
	// 데이터 단건 조회
	@Override
	public BoardVO get(Long bno) {
		log.info("get.........." + bno);
		return mapper.read(bno);
	}
	// 데이터 수정
	@Override
	public boolean modify(BoardVO board) {
		log.info("modify..........");
		return mapper.update(board) == 1; // 수정할 데이터가 존재할 경우 반환한다
	}
	// 데이터 삭제
	@Override
	public boolean remove(Long bno) {
		log.info("remove..........");
		return mapper.delete(bno) == 1;
	}
	// 전체 데이터 조회(페이징 처리)
	@Override
	public List<BoardVO> getList(Criterial cri) {
		log.info("getList..........");
		return mapper.getListWithPaging(cri);
	}
	@Override
	public int getTotal(Criterial cri) {
		log.info("getTotal..........");
		return mapper.getTotal(cri);
	}

}
