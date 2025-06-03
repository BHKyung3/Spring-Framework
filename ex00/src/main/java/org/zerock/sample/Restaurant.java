package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // root-context.xml에서 사용하기 위에 기재함
public class Restaurant {
	
	// private Chef chef = new Chef();
	@Autowired
	private Chef chef; // 위에랑 같은 의미
	
	public void sample() {
		chef.eat();
	}

}
