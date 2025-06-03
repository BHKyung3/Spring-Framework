package org.zerock.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

//서버 실행하지 않고 테스 계정에서 확인할 때 사용(11~13번째 줄)
@RunWith(SpringJUnit4ClassRunner.class) // porm.xml에서 테스트 환경으로 수정해야함
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j // log.info 이걸 기재하기 위해 사용
public class SampleTests {
	
	@Autowired // 미기재 시 실행이 되지 않음(null 값) 생성자 주입이라고 한다 : 미 기재 시 실행되지 않는 건 객체가 따로 없기 때문이다
	// 의미 : 메모리 공간(root-context) 안에 있는 내용을 실행해줘(메모리 공간에 있는 거 참조하고 있음)
	// 하나를 가지고 사용 가능(일종의 싱글톤 패턴과 비슷)
	private Restaurant restaurant = new Restaurant();
	
	@Test // 테스트임을 나타냄
	public void testRest() {
		log.info("--------------------");
		log.info("restaurant : " + restaurant);
		restaurant.sample();
	}

}
