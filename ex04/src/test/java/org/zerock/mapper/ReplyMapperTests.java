
// 댓글 기능에 대한 단위 테스크 코드

package org.zerock.mapper;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Criterial;
import org.zerock.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class) // JUnit에서 Spring과 연동해서 테스트
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") // 스프링 설정 파일 위치 지정
@Log4j
public class ReplyMapperTests {

	@Autowired
	private ReplyMapper mapper;
	// 댓글을 어느 게시글 번호(bno)에 작성할 건지 지정한 게시글 번호 목록, 댓글 테스트할 때 랜덤하게 골라서 사용
	private Long[] bnoArr = {
			852321L, 852301L, 852297L, 851996L, 851995L
	};
	// 댓글 9개를 만들어서 DB에 등록하는 테스트, DB에 댓글 9개가 뜨는지 확인
	@Test
	   public void testCreate() {
	      IntStream.range(1, 10)
	      .forEach(i -> {
	         ReplyVO vo = ReplyVO.builder()
	               .bno(bnoArr[i%5]) // 게시글 번호, bno는 배열에서 순환하면서 랜덤으로 가져옴
	               .reply("댓글 테스트" + i) // 댓글 내용
	               .replyer("replyer" + i) // 작성자
	               .build();
	         
	         mapper.insert(vo);
	      });
	}
	// 댓글을 조회해서 로그로 출력하는 테스트(1번) // 1번 댓글 읽기
	@Test
	public void testRead() {
		log.info(mapper.read(1L));
		
	}
	// 댓글 9번 삭제여부 테스트
	@Test
	public void testDelete() {
		log.info(mapper.delete(9L));
	}
	// 댓글 8번 수정 테스트
	@Test
	public void testUpdate() {
		
		ReplyVO vo = ReplyVO.builder()
				.reply("댓글 수정내용")
				.rno(8L)
				.build();
		
		log.info(mapper.update(vo));
	}
	// 851991에 달린 댓글 목록을 페이징 처리해서 가지고 오는지 테스트
	@Test
	public void testGetList() {
		Criterial cri = new Criterial(); // cri : 페이지 정보를 담은 객체
		
		Long bno = 851991L;
		
		mapper.getListWithPaging(cri, bno).forEach(ReplyVO -> log.info(bno));
	}
	
	@Test
	public void testList2() {
		Criterial cri = new Criterial(1, 2); // 댓글 항목 1~2번째만 출력
		
		mapper.getListWithPaging(cri, 191L).forEach(list -> log.info(list));
	}
	
	}

