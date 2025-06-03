
// 전달된 객체를 생산하기 위해 SampleVO 클래스 작성

package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // 모든 속성을 사용하는 생성자를 위해 기재
@NoArgsConstructor // 비어 있는 생성자를 만들기 위해 기재
public class SampleVO {
	
	private Integer mno;
	private String firstName;
	private String lastName;

}
