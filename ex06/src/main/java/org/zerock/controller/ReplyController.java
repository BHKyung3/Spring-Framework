package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criterial;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequiredArgsConstructor
@Log4j
@RequestMapping("/replies")
public class ReplyController {
	
	private final ReplyService service;
	
	@PreAuthorize("isAuthenticated()")
	// 댓글 데이타 등록(저장) -> 댓글을 작성하면, JSON 데이터를 받아서 DB에 저장하고, 성공 여부에 따라 "success" 또는 에러 응답을 보내는 메서드
	@PostMapping(value = "/new") // 댓글을 새로 저장할 때 /replies/new으로 호출
	// ResponseEntity<String> create : 클라이언트 요청에 대한 HTTP 응답을 만들고 값을 리턴한다
	// @RequestBody ReplyVO vo : 클라이언트가 보낸 JSON 데이터를 자바 객체(ReplyVO)로 자동 변환
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){
		log.info("ReplyVO" + vo);
		
		int intsertCount = service.register(vo); // register() 메서드 호출해 댓글을 DB에 저장 시도
		
		if(intsertCount == 1) {
			// 저장 성공
			return new ResponseEntity<>("success", HttpStatus.OK);
		} else {
			// 저장 실패
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// 댓글 목록을 페이지 별로 가져오기 -> 특정 게시글(bno)의 댓글들을 지정한 페이지(page)에 맞춰 JSON 형식으로 가져오는 GET API
	@GetMapping(value = "/pages/{bno}/{page}", // /replies/pages/{bno 번호}/{몇 페이지}"으로 호출
			produces = MediaType.APPLICATION_JSON_VALUE) // 결과를 JSON 형식으로 응답
	public ResponseEntity<ReplyPageDTO> getList( // 
			@PathVariable("bno") Long bno,
			@PathVariable("page") int page
			){
		log.info("getLlist.....");
		
		Criterial cri = new Criterial(page, 10);
		
		return new ResponseEntity<>(service.getListPage(cri, bno), HttpStatus.OK);
	}
	
	// 댓글 단건(건별) 데이타 가져오기
	@GetMapping(value = "/{rno}",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
		log.info("get....." + rno);
		
		return new ResponseEntity<ReplyVO>(service.get(rno), HttpStatus.OK);
	}
	
	// 댓글 데이터 삭제
	@PreAuthorize("principal.username == #vo.replyer")
	@DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE}) // EXT_PLAIN_VALUE 문자열 반환
	public ResponseEntity<String> remove(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno){ // @RequestBody ReplyVO vo : JSON으로 된 데이터를 받도록 수정,
		log.info("remove....." + rno);
		
		return service.remove(rno) == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
										: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 댓글 데이터 수정
	// method : 요청할 값이 다수일 경우 사용하여 {} 안에 기재하여 사용
	// 메소드가 PUT 또는 PATCH 이면 값을 json 형식을 반환하여 전달한다
	@PreAuthorize("principal.username == #vo.replyer")
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
					value = "/{rno}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno){
		
		vo.setRno(rno);
		log.info("modify....." + rno);
		
		return service.modify(vo) == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	

}
