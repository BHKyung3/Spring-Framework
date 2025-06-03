package org.zerock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
@WebAppConfiguration // 서버 없이 구동할 때 사용
public class BoardControllerTests {
	
	@Autowired // 웹 관련 Bean(생성된 객체) 관리
	private WebApplicationContext ctx;
	
	// 서버를 실행하지 않고도 HTTP 요청과 응답을 시뮬레이션 하기 위한 도구
	private MockMvc mockMvc;
	
	@Before // Spring MVC 애플리케이션에서 통합 테스트 수향, 실제 서버를 실행하지 않고 컨트롤러 동작을 테스트 가능
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	// 데이터 전체 조회
	@Test
	public void test() throws Exception {
		log.info(
			mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
			.andReturn()
			.getModelAndView()
			.getModelMap()
		);
	}
	// 데이터 추가
	@Test
	public void testRegister() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
			.param("title", "테스트 새글 제목")
			.param("content", "테스트 새글 내용")
			.param("writer", "테스트 새글 작성자")
			).andReturn()
		.getModelAndView()
		.getViewName();
		
		log.info("=====>" + resultPage);
	}
	// 데이터 단건 조회
	@Test
	public void testGet() throws Exception {
		log.info(
			mockMvc.perform(MockMvcRequestBuilders
			.get("/board/get")
			.param("bno", "4"))
			.andReturn()
			.getModelAndView()
			.getModelMap()
		);
	}
	// 데이터 삭제
	@Test
	public void testDelete() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
			.param("bno", "8")
			).andReturn()
		.getModelAndView()
		.getViewName();
		
		log.info("=====>" + resultPage);
	}
	// 데이터 수정
	@Test
	public void testModify() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
			.param("title", "안쪼르곰")
			.param("content", "너는 쪼르곰")
			.param("writer", "안안 쪼르곰 너는 안쪼르곰")
			.param("bno", "5")
			).andReturn()
		.getModelAndView()
		.getViewName();
		
		log.info("=====>" + resultPage);
	}
}
