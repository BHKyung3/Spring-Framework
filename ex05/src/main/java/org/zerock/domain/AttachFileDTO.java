package org.zerock.domain;

import lombok.Data;

@Data // 첨부파일의 정보를 저장하는 DTO 클래스
public class AttachFileDTO {
	
	private String fileName;
	private String uploadPath;
	private String uuid;
	private boolean image;

}
