package org.zerock.repository;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.dto.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{
			"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
			"file:src/main/webapp/WEB-INF/spring/root-context.xml"	
		}
)
@Log4j
public class BoardRepositoryTests {

	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	public void test() {
		log.info("boardRepository >> " + boardRepository);
	}
	
	
	@Test
	public void selectAlltest() {
		
		List<BoardVO> list = boardRepository.selectAllBoards();
		for(BoardVO vo : list)
			log.info(vo);
		
		log.info("--------------------------------");
		
		boardRepository.selectAllBoards()
			.forEach(board-> log.info(board));
	}
	
	@Test
	public void selectOneByNumTest() {
		
		BoardVO vo = boardRepository.selectOneByNum(12);
		log.info("vo >> " + vo);
	}
	
	@Test
	public void insertBoardTest() {
		BoardVO vo = new BoardVO();
		
		vo.setName("안쪼꼼");
		vo.setPass("4567");
		vo.setEmail("test@naver.com");
		vo.setTitle("똔대지");
		vo.setContent("못난이");
		
		boardRepository.insertBoard(vo);
	}
	
	@Test
	public void updateBoardTest() {
		BoardVO vo = new BoardVO();
		
		vo.setName("안쪼꼼");
		vo.setPass("4567");
		vo.setEmail("gg@naver.com");
		vo.setTitle("똔대지");
		vo.setContent("못난이 안쪼르곰");
		vo.setNum(61);
		
		boardRepository.updateBoard(vo);
	}
	
	@Test
	public void deleteBoard() {
		boardRepository.deleteBoard(64);
	}
	
	@Test
	public void updateReadCount() {
		boardRepository.updateReadCount(61);
	}

}