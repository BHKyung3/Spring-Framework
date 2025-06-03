
// 페이징 처리 후 순서 보기(다음페이지, 이전페이지 선택 창)

package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@Getter // 조회만 하겠다
@ToString
@Log4j
public class PageDTO {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	// 전체 레코드 개수(전체 데이터 개수)
	private int total;
	// 페이지정보, 페이지 당 레코드 개수
	private Criterial cri;
	
	public PageDTO(Criterial cri, int total) {
		this.cri = cri; // pageNum=15&amount=10
		this.total = total; 
		
		// endPage
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
		
		this.startPage = this.endPage -9;
		
		// 전체 페이지 목록에서 마지막 페이지
		int realEnd = (int)(Math.ceil((total*1.0)/cri.getAmount()));
		
		if(realEnd < this.endPage) { // 총 목록 / 10 = ??, 올림처리
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
		
		log.info("-----DTO");
		log.info(this.startPage);
		log.info(this.endPage);
		log.info(this.total);
	}

}
