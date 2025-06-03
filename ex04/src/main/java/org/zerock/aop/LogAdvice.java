package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Component // 빈을 등록하는 가장 대빵인 어노테이션(@Service, @Controller 외)
@Log4j
@Aspect // ?
public class LogAdvice {
	
	// 모니터링 역할? 
	// @Before, @After 두 가지 다 기재 가능
	// .. 어떤 매개 변수가 와도 신경쓰지 안겠다 함수의 인자값도 신경쓰지 않겠다
	// SampleService로 시작하는 클래스 중 동작만 한다면 어떤게 오든 상관 없음
	
//	@AfterThrowing("execution(* org.zerock.service.SampleService*.*(..))")
	@Around("execution(* org.zerock.service.SampleService*.*(..))") // 시간 구하기
	public Object logTime(ProceedingJoinPoint pjp) {
		 // System.currentTimeMillis() 또는 System.nanoTime() 등등
		long start = System.nanoTime();
		
		log.info("Tatget : " + pjp.getTarget());
		log.info("Param : " + Arrays.toString(pjp.getArgs()));
		
		Object result = null;
		
		try {
			result = pjp.proceed(); // 함수 호출 처리
		} catch(Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.nanoTime();
		
		log.info("TIME : " + (end-start));
		return result;
	}

}
