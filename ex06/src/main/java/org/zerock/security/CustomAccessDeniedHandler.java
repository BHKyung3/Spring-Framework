package org.zerock.security;

// 스프링 시큐리티가 이미 표준 인터페이스를 제공하고 있어 따로 만들지 않고 구현체 사용 가능
// XML 설정에서 ref로 구현체만 지정해주면 자동으로 연결 + bean 등록 필요

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler { // 에러가 나면 스프링에 내장되어 있는게 아닌 내가 만든 에러를 사용하겠다
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		log.error("Access Denied Handler.....");
		log.error("Redirect.....");
		
		response.sendRedirect("/accessError");
	}

}
