package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Criterial;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServicempl implements ReplyService {
	
	private final ReplyMapper mapper;
	private final BoardMapper boardMapper;
	
	// 댓글 추가
	@Override
	@Transactional // 실패 했다면 다른 하나도 실패하게 한다
	public int register(ReplyVO vo) {
		
		boardMapper.updateReplyCnt(vo.getBno(), 1);
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

	@Transactional
	@Override
	public int remove(Long rno) {
		
		ReplyVO vo = mapper.read(rno); // rno만 가지고 getBno 값을 알 수 없어 기재
		
		boardMapper.updateReplyCnt(vo.getBno(), -1); // 삭제 시 댓글 1개 감소
		
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
