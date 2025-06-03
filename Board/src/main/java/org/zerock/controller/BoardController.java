package org.zerock.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.dto.BoardVO;
import org.zerock.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/*
 	/board/boardList -> 전체데이터 반환
 	/board/view -> 상세 페이지
 */

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j
public class BoardController {
	
	private final BoardService boardService;
	// 게시글 목록 전체 보기
	@GetMapping("/boardList")
	public String boardList(Model model) {	
		
		List<BoardVO> list =  boardService.selectAllBoards(); // 전체 데이터 가져오기
		
		model.addAttribute("boardList", list); // boardList 값을 담아서
		
		return "boardList"; // boardList.jsp로 전달하기
	} // 게시글 목록 전체 보기 끝
	// 게시글 등록 
	@GetMapping("/register") // 게시글 등록, get 요청으로 보냈으니 getMapping으로 받음
	public String register() {
		return "boardRegister"; // register.jsp로 찾아감
	}
	// 게시글 등록 후 저장
	@PostMapping("/register") // 게시글 작성 후 등록 버튼 누르면 post방식으로 보냈으니 받아서
	public String insertBoard(BoardVO vo) {

		boardService.insertBoard(vo); // 화면 저장
		
		return "redirect:/board/boardList"; // 저장 후 화면은 리스트로 이동
	}
	// 상세페이지 이동
	@GetMapping("/view")   // @RequestParam : URL이나 폼(form) 데이터로 넘어온 값을 메소도의 매개변수로 받아오는것, jsp에서 req..파라미터와 동일한 의미
							// 사용자가 보낸 요청 안에 num이라는 값이 있다면 그 값을 int num 변수로 받아오겠다
	public String viewBoard(@RequestParam("num") int num, Model model) { // @RequestParam("num") 에 기재된 num은 DB이름과 동일해야한다
		
		boardService.updateReadCount(num); // 조회수 증가
		BoardVO vo =  boardService.selectOneByNum(num); // DB에서 num(기본키) 값에 기재된 전체 데이타를 가져와서 vo에 저장
		model.addAttribute("board", vo); // vo에 저장된 num 값의 데이타를 board 변수에 담아서 boardView.jsp로 전달
		
		return "boardView";
	} // 삭제 버튼 클릭 시 윈도우 창
	@GetMapping("/check")
	public String checkBoard(@RequestParam int num, Model model) {
		model.addAttribute("num", num);
		
		return "boardCheck";
	}
	// 원도우 창 뜨고 유효성 검사와 삭제 처리
	@PostMapping("/check")
	public String checkPost(@RequestParam int num, @RequestParam String pass, Model model) {
		// 서비스 호출해서 true(비밀번호 맞음), false(비밀번호 틀림) 반환 받는다.(BoardService.java 파일 참고)
		boolean check = boardService.checkPassword(num, pass);
		
		if(check) {
			model.addAttribute("num", num); // 비밀번호 맞음
			return "checkSuccess";
		} else {
			model.addAttribute("message", "비밀번호가 틀렸습니다."); // 비밀번호 틀렸다 문구 노출
			model.addAttribute("num", num); // 비밀번호 2회 틀렸을 시 오류가 발생되어 num값을 전달하기 위해 기재
			return "boardCheck";
		} // 위도우 창 뜨고 유효성 검사와 삭제 처리 끝
	} 
		
	@GetMapping("/delete")
	public String deleteGet(@RequestParam int num) {
		boardService.deleteBoard(num);
		return "redirect:/board/boardList"; // 저장 후 화면은 리스트로 이동
	}
		
	// 게시글 수정
	@GetMapping("/update")
	public String updateGet(@RequestParam int num, Model model) {
		BoardVO vo = boardService.selectOneByNum(num);
		model.addAttribute("board", vo);
	    return "boardUpdate";
	}
	
	@PostMapping("/update")
	public String updatePost(BoardVO vo) {
		boardService.updateBoard(vo);
		return "redirect:/board/view?num=" + vo.getNum();
	}
}