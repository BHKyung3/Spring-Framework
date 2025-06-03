package org.zerock.service;

import org.springframework.stereotype.Service;

@Service
public class SampleServicempl implements SampleService {

	@Override
	public Integer doAdd(String str1, String str2) throws Exception {
		// 정수로 변환 시켜 받아준다
		return Integer.parseInt(str1) + Integer.parseInt(str2);
	}

	@Override
	public Integer doMul(Integer n1, Integer n2) {
		System.out.println("-----doMul-----");
		return n1*n2;
	}

}
