package org.zerock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController //view화면을 찾지 않고, response 값만 전달한다
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	// 단순 문자열 반환
	@GetMapping(value = "/getText", produces = "text/plain; charset=utf-8")
//	@GetMapping(value = "/getText", produces = MediaType.TEXT_PLAIN_VALUE)
//	@GetMapping(value = "/getText", produces = MediaType.TEXT_HTML_VALUE)
	public String getText() {
		log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요";
	}
	// SampleVO를 리턴하는 메서드 // xml, json 방식의 데이터를 생성할 수 있도록 작성
	@GetMapping(value = "/getSample",
			// @GetMapping, @RequestMapping의 produces 속성은 반드시 지정해야하는 것이 아니므로 생략도 가능
				produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
							MediaType.APPLICATION_XML_VALUE})
	public SampleVO getSample() {
		return new SampleVO(112, "스타", "로드");
	}
	
	// SampleVO 여러 데이터를 한 번에 전송하기 위해 배열이나 리스트, 맵 타입의 객체들을 전송할 때
	@GetMapping(value = "/getList")
//				, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
// 				MediaType.APPLICATION_XML_VALUE}) ==> 자동기재로 기재하지 않아도 무관
	public List<SampleVO> getList(){
		
		// IntStream : 정수 값을 1~9까지 하나씩 흘려보낸다 // mapToObj : 흘려 보낸 값을 하나씩 받아 객체를 생성 후 다시 흘려보낸다 // collect : 흘러온 객체를 모아 리스트에 담는다
		return IntStream.range(1, 10)
				.mapToObj(i -> new SampleVO(i, i+"First", i+"Last"))
				.collect(Collectors.toList()); 
	
/*		↑ 위에 return ~ ; 까지의 코드와 동일하게 출력되며 간결하게 표현하기 위해 사용 // 하지만 밑에 있는게 일반적으로 사용됨, 두가지 다 사용 할 줄 알아야함
		List<SampleVO> list = new ArrayList<SampleVO>();
		
		for(int i=1; i<9; i++) {
			SampleVO vo = new  SampleVO(i, i+"First", i+"Last");
			list.add(vo);
		}
		
		return list; */
}
	// json 반환하겠다, 맵의 경우 '키'와 '값'을 가지는 하나의 객체로 간주
	@GetMapping(value = "/getMap",
			produces = {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	public Map<String, SampleVO> getMap(){
		Map<String, SampleVO> map = new HashMap<String, SampleVO>();
		
		map.put("First", new SampleVO(111, "그루트", "주니어"));
		
		return map;
	}
	// rest 방식으로 호출하는 경우 화면이 아닌 데이터 자체를 전송하는 방식으로 처리되기 때문에 데이터를 요청한 쪽에서는 정상인지 비정상인지 구분할 수 있는 확실한 방법을 제공해야함
	// ResponseEntity : 데이터와 함께 http 헤더의 상태 메세지를 같이 전달하는 용도로 사용
	@GetMapping(value = "/check", params = {"height", "weight"}, // 매개변수 역할
				produces = {MediaType.APPLICATION_JSON_VALUE,
						MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<SampleVO> check(Double height, Double weight) { // check는 반드시 height, weight를 파라미터로 전달 받음
		
		SampleVO vo = new SampleVO(0, "" + height, "" + weight);
		
		ResponseEntity<SampleVO> result = null;
		
		if(height < 150) { // 만약에 height 값이 150보다 작다면
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo); // BAD_GATEWAY(502 오류) 데이터 전송
		} else { // 그렇지 않다면
			result = ResponseEntity.status(HttpStatus.OK).body(vo); // 정상 데이터 전송
		}
		
		return result;
	}
	
	@GetMapping(value = "/product/{cat}/{pid}") // 경로명에 기재된 내용을 값으로 취하겠다 {}로 처리된 부분은 컨트롤러의 메서드에서 변수로 처리 가능
	public String[] getPath(
			@PathVariable("cat") String c1, // 스프링 MVC에서는 @PathVariable 어노테이션을 이용해 URL 상에 경로의 일부를 파라미터로 사용할 수 있음
			@PathVariable("pid") String p1 // {}를 이용하여 변수명 지정, 지정된 이름의 변숫값을 얻을 수 있음, 값을 얻을 때는 기본 자료형을 사용할 수 없음
//			@PathVariable() String cat,
//			@PathVariable() String pid => 요것도 사용 가능(위에랑 출력 값 동일)
			) {
		return new String[] {"category : " + c1, "productId : " + p1};
	}
	// @RequestBody Ticket ticket ==> json 데이터를 입력 받는다(요청 측에서 json으로 서버에게 값을 전달)
	// @PostMapping을 기재하는 이유 : 요청한 내용을 처리하기 때문에 일반적인 파라미터 전달 방식을 사용할 수 없음
	@PostMapping("/ticket")
	public Ticket convent(@RequestBody Ticket ticket) { // @RequestBody : 전달된 요청의 내용을 이용해서 해당 파라미터의 타입으로 변환 요구
		log.info("ticket : " + ticket);
		
		return ticket;
	}

}
