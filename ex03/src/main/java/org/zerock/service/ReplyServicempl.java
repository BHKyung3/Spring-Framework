package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.Criterial;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServicempl implements ReplyService {
	
	private final ReplyMapper mapper;

	@Override
	public int register(ReplyVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		return mapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criterial cri, Long bno) {
		return mapper.getListWithPaging(cri, bno);
	}
	// 댓글 페이징 조회에 관련된 서비스
	@Override
	public ReplyPageDTO getListPage(Criterial cri, Long bno) {
		return new ReplyPageDTO(mapper.getCountByBno(bno), // // mapper.getCountByBno(bno) : 댓글 개수, 
								mapper.getListWithPaging(cri, bno)) ; // mapper.getListWithPaging(cri, bno)) : 전체 목록
	}
	
	

}
