package org.zerock.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.dto.BoardVO;
import org.zerock.persistence.DataSourceTests;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TimeMapperTests {

	@Autowired
	private TimeMapper timeMapper;
	
	@Test
	public void test() {
		log.info("-------------------------");
		log.info(timeMapper.getClass().getName());
		log.info(timeMapper.getTime());
	}
	
	@Test
	public void testGetTime2() {
		log.info("-------------------------");
		log.info(timeMapper.getTime2());
	}
	
	@Test // 전체 데이터 조회 테스트
	public void testAllList() {
		List<BoardVO> list = timeMapper.selectAllList();
		
		for(BoardVO vo : list)
			log.info(vo);
	}
	
	@Test // 단건 데이터 조회 테스트
	public void testSelectOne() {
		log.info(timeMapper.selectOneByNum(5));
	}
	
	@Test // 데이터 추가
	public void testInsert() {
		BoardVO vo = new BoardVO();
		
		vo.setName("안쪼꼼");
		vo.setEmail("apple@naver.com");
		vo.setPass("1234");
		vo.setTitle("똔대지 안쪼꼼");
		vo.setContent("안쪼르곰 너는 쪼르곰");
		
		timeMapper.insertBoard(vo);
	}

}
