package org.zerock.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller // 스프링이 "이 클래스는 웹 요청을 처리하는 애구나~" 하고 인식하게 해주는 역할
@RequestMapping("/sample") // 공통 경로 지정
@Log4j // Lombok 사용
public class SampleController {
	
	/*
	 	반환타입 : void의 경우
	 	return이 void인 경우 : view화면은 경로명으로 찾는다. /view/sample.jsp
	*/
	// localhost:8080/sample 요청 시 여기에서 실행
	// /view/sample.jsp
	@RequestMapping("/") // / : /view 밑에 /sample.jsp 찾아감
	public void basic() {
		log.info("basic----------");
	}
	// localhost:8080/sample/basic 요청 시 여기에서 실행
	// /view/sample/basic.jsp
	@RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST}) // basic : /view 밑에 /sample 밑에 /basic.jsp 찾아감
	public void basicGet() { // 배열로 묶어줘야함
		log.info("basic get----------");
		
		// view/sample/basic.jsp 찾아간다
	}
	// localhost:8080/sample/basicOnlyGet 요청 시 여기에서 실행
	// /view/sample/basicOnlyGet.jsp으로 찾아감
	@GetMapping("/basicOnlyGet") // basicOnlyGet : /view 밑에 /sample 밑에 /basicOnlyGet.jsp 찾아감
	public void basicGet2() {
		log.info("basic get only get----------");
		
		// basicOnlyGet : view/sample/basicOnlyGet.jsp 찾아간다
	}
	// localhost:8080/sample/ex01?name=??&age=?? 으로 기재해야 응답 받음
	// /view/ex01.jsp으로 찾아감
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(dto);
		return "ex01";
	}
	// localhost:8080/sample/ex02?name=??&age=?? 으로 기재해야 응답 받음
	// /view/ex02.jsp으로 찾아감
	@GetMapping("/ex02") // 기본 자료형(값 전달을 위해 model 사용, 직관적으로 알기 위해 @RequestParam 기재하는 것이 좋음)
	public String ex02(@RequestParam String name, @RequestParam int age, Model model) {
		log.info(name);
		log.info(age);
		model.addAttribute("name", name); // ex02.jsp 페이지에 name, age 값을 전달하여 보낸다
		model.addAttribute("age", age);
		return "ex02";
	}
	/*/@InitBinder // 화면상에 원하는 형식으로 값을 받기 위해 사용
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, 
			new CustomDateEditor(dateFormat, false));
	}*/
	// localhost:8080/sample/ex03?title=??&dueDate=?? 으로 기재해야 응답 받음 // 객체 형태 ↑ 위에와 동일하게 출력 됨, 동시 사용 불가
	// /view/ex03.jsp으로 찾아감
	@GetMapping("/ex03")
	public String ex03(TodoDTO todoDTO) {
		log.info(todoDTO);
		return "ex03";
	}
	
	/* @GetMapping("/ex04")
	public String ex04(SampleDTO dto, int page, Model model) {
		log.info(dto);
		log.info(page);
		model.addAttribute("page", page); // 넘겨줄 값을 담아서 sample/ex04에 넘겨준다
		model.addAttribute("SampleDTO", dto);
		return "sample/ex04";
	} */
	// 객체는 담아줄 값이 없어도 전달 가능하나 기본자료형은 담아줄 값이 있어야 전달 가능(model에 담아 전달)
	// 위에랑 출력 값 같음 @ModelAttribute : 클라이언트 요청이 오면 페이지 값을 받아 화면까지 전달하겠다는 의미
	/* @GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page, Model model) {
		log.info(dto);
		log.info(page);
	//	model.addAttribute("page", page); // 넘겨줄 값을 담아서 sample/ex04에 넘겨준다
		model.addAttribute("SampleDTO", dto);
		return "sample/ex04";
	} */
	// localhost:8080/sample/ex04?name=??&age=?? 으로 기재해야 응답 받음
	// /view/sample/ex04.jsp + SampleDTO
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto) {
		log.info(dto);
		return "sample/ex04";
	}
	// localhost:8080/sample/rttr?name=??&age=?? 으로 기재해야 응답 받음
	// /view/sample/ex04.jsp
	@GetMapping("/rttr")
	public String rttr(SampleDTO dto, RedirectAttributes rttr) {
		rttr.addFlashAttribute("name", dto.getName());
		rttr.addFlashAttribute("age", dto.getAge());
		return "redirect:/sample/ex04";
	}
	// localhost:8080/sample/ex06?name=??&age=?? 으로 기재해야 응답 받음
	// /view/sample/ex06.jsp
	// @ResponseBody java 객체를 반환 시킬 때 json 데이터로 반환 시킨다(반환타입은 json으로 넘겨준다), 자바 객체를 json으로 변환해서 client에게 json 값만 전달
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06----------");
		
		/* SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		
		return dto; */
		
		// lombok 사용으로 객체를 생성하여 반환할 때 사용하는 코드
		return SampleDTO.builder().name("홍길동").age(10).build();
	}
	// localhost:8080/sample/ex06_1 + json 데이터 전달(postman 등등)
	// @RequestBody : json을 java 객체로 반환하겠다
	@GetMapping("/ex06_1")
	public String ex06_1(@RequestBody SampleDTO dto) { // @RequestBody 자바 객체를 값만 전달하겠다?
		log.info("/ex06_1----------");
		log.info(dto);
		return "ex06_1";
	}
	
	@GetMapping("/ex07") // ex07 주소로 get 요청이 오면 실행되는 컨트롤러 메서드
	public ResponseEntity<String> ex07(){ // ResponseEntity<String> :  HTTP 응답 전체를 직접 조작하겠다는 뜻
		
		// 직접 응답 메시지(ResponseEntity)를 구성해서 클라이언트에게 보낸다는 뜻(상태코드, 헤더, 바디를 자유롭게 설정할 수 있기 때문에 다양한 응답 형태를 만들 수 있음)
		// {"name" : "홍길동"} 
		String msg = "{\"name\": \"홍길동\"}"; // 응답 본문(body)에 JSON 문자열 직접 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/json;charset=utf-8"); // 클라이언트에게 "이건 JSON이야!" 하고 알려주는 역할
		return new ResponseEntity<String>(msg, headers, HttpStatus.CREATED);
	}
}
