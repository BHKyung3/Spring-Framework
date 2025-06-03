package org.zerock.controller;

// 접근 권한이 없을 때 실행되는 controller

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommonController {

	// 로그인은 성공 했으나 권한이 맞지 않은 경우
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		log.info("access Denid : " + auth);
		model.addAttribute("msg", "접근이 거부되었습니다.");
	}
	
	// 로그인 실패 또는 로그아웃 성공 시 나타나는 페이지용 컨트롤러
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
		
		log.info("error : " + error);
		log.info("logout : " + logout);
		
		if(error != null) {
			model.addAttribute("error", "Login Error Check Your Account"); // 로그인 잘못 입력 했을 경우
		}
		
		if(logout != null) {
			model.addAttribute("logout", "Logout!!!!!"); // 로그아웃 시 노출
		}
	}
	
	// 로그아웃
	
	@GetMapping("/customLogout")
	public void logoutGET() {
		log.info("custom logout");
	}
}
