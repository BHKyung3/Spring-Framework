package org.zerock.mapper;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;

import lombok.extern.log4j.Log4j;


// 3가지 테스트에 기본 셋팅
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	// DB 쿼리를 실행할 Mapper 주입
	@Autowired
	private BoardMapper mapper;
	
	// bno 1번 조회
	@Test
	public void testRead() {
		log.info(mapper.read(1L));
	}
	// 전제데이터 조회
	@Test
	public void testGetList() {
		List<BoardVO> list = mapper.getList(); // list : DB 데이터 참조
		
		for(BoardVO vo : list) {
			log.info(vo);
		}
	}
	// 데이터 추가
	@Test
	public void testInsert() {
		BoardVO vo = BoardVO.builder().title("test title").content("test content")
				.writer("test writer")
				.build();
		
		mapper.insert(vo);
	}
	// 데이터 추가 + bno 번호 확인
	@Test
	public void testInsertKey() {
		BoardVO vo = BoardVO.builder().title("test title").content("test content")
				.writer("test writer")
				.build();
		
		mapper.insertSelectKey(vo);
	}
	// 데이터 삭제
	@Test
	public void testDelete() {
		int result = mapper.delete(7L); // 7L : 7번째 bno이란 의미
		log.info("result >>>>> " + result);
	}
	// 데이터 수정
	@Test
	public void testUpdate() {
		BoardVO vo = BoardVO.builder()
			.title("안쪼꼼")
			.content("안쪼르곰")
			.writer("뱃짤똥똥")
			.bno(5L)
			.build();
		
		int result = mapper.update(vo);
		log.info("result >>>>> " + result);
	}
	// 페이징 처리 테스트
	@Test
	public void testPaggin() {
		List<BoardVO> list = mapper.getListWithPaging(new Criterial(3, 10));
		
		list.forEach(board -> log.info(board));
	}
	// 조건 검색 여부 테스트
	@Test
	public void testSearch() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("T", "유빼꼼"); // key : T, value : 어버이날 (A) 요거요거
		map.put("C", "짜장묜");		// key : C, value : 은혜 (B) 요거요거
		map.put("W", "안쪼꼼");	
		
		// outer 안에 T, C, W 정보가 다 들어있다
		Map<String, Map<String, String>> outer = new HashMap<>();
		
		outer.put("map", map); // key : map, value : (A) + (B) 여기로
		
// 		log.info((outer.get("map")).get("T")); > 테스트
		
		List<BoardVO> list = mapper.searchTest(outer);
		
		log.info("--------------------");
		log.info(list);
	}
	// 조건 검색 여부 테스트 최종본
	@Test
	public void testSearch2() {
		Criterial cri = new Criterial();
		
		cri.setKeyword("안쪼꼼");
		cri.setType("TW");
		
		mapper.getListWithPaging(cri).forEach(board -> log.info(board));
	}
	
	@Test
	public void testgetTotal() {
		Criterial cri = new Criterial();
		
		cri.setKeyword("안쪼꼼");
		cri.setType("TW");
		
		log.info("getTotal : ");
		log.info(mapper.getTotal(cri));
	}
}
