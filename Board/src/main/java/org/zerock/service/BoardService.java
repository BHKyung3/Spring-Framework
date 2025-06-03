package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.dto.BoardVO;
import org.zerock.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service // 해당 클래스가 서비스 클래스임을 스프링에 알리는 기능(스프링이 자동으로 객체(Bean)로 등록할 때 사용
@RequiredArgsConstructor // final 필드에 대해 생성자를 자동 생성(Lombok)(생성자 주입을 간편하게 만들 때 사용)
public class BoardService {
	
	// 필드 주입 > 객체의 필드(멤버변수)에 직접 @Autowired 붙여서 주입
//	@Autowired
//	private BoardRepository boardRepository; 
	
	// 생성자 주입 > 생성자를 통해 의존 객체를 주입
	// 외부에서 직접 접근 불가하며 수정도 불가한 객체 선언
	private final BoardRepository boardRepository; // BoardRepository를 이 클래스 안에서 사용(의존성 주입), DB 작업을 위힘할 때 사용
	
	// 외부에서 접근 가능한 BoardVO 객체들을 리스트 형태로 반환하고 모든 게시그를 조회하며 DB에 내용을 전체 가지고 오는 기능을 호출하여 보여준다
	public List<BoardVO> selectAllBoards() {
			return boardRepository.selectAllBoards();
		}
	// 게시글 단건 조회 시 
	// num로 게시글을 선택하여 BoardVO 하나를 리턴하여 실행 결과를 호출한 쪽에 돌려준다(상세페이지에서 활용)
	public BoardVO selectOneByNum(int num) {
		return boardRepository.selectOneByNum(num);
	}
	// 게시글 작성 시
	// 반환 값이 없고 게시글을 추가는 기능, 게시글 하나를 매개 변수로 받는다
	public void insertBoard(BoardVO vo) {
		// DB에 새 게시글을 추가하라고 한다
		boardRepository.insertBoard(vo);
	}
	public void updateBoard(BoardVO vo) {
		boardRepository.updateBoard(vo);
	}
	public void deleteBoard(int num) {
		boardRepository.deleteBoard(num);
	}
	public void updateReadCount(int num) {
		boardRepository.updateReadCount(num);
	}
	// 게시글 삭제 시 DB 비밀번호와 기재한 비밀번호가 동일한지 확인
	public boolean checkPassword(int num, String pass) {
		
		BoardVO vo = boardRepository.selectOneByNum(num);
		
		// 입력받은 비밀번호가 DB에 저장된 비밀번호와 같은가?
		if(vo.getPass().equals(pass)) {
			return true;
		} else {
			return false;
		}
	}
}
