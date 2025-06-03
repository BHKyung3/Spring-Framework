package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder // 객체 생성하여 반환할 때 lombok 사용도 가능, 객체를 직관적으로 사용 가능하며 사용 시에는 @AllArgsConstructor, @NoArgsConstructor 두가기를 필수로 기재해줘야함
@AllArgsConstructor
@NoArgsConstructor
public class SampleDTO {
	
	private String name;
	private int age;
	
	/* @NoArgsConstructor 의미
	public SampleDTO{
		
	} */
	
	/* @AllArgsConstructor 의미
	public SampleDTO(String name, int age) {
		this.name = name;
		this.age = age;
	} */

}
