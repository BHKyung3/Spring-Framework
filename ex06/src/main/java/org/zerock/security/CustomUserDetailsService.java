package org.zerock.security;

import javax.management.loading.PrivateClassLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.MemberMapper;
import org.zerock.security.domain.CustomUser;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override							// AuthicationProvider가 전달한 username을 받는다. // 이제 DB가서 데이터 가져와야지
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // String username : username을 받을 준비
		
		log.warn("Load User By Username : " + username); // 로그를 경고(warning) 수준으로 출력하라는 뜻
		
		MemberVO vo = memberMapper.read(username);
	
		return vo == null ? null : new CustomUser(vo); // vo가 null이면 null 반환, null 아니면 CustomUser(vo) 반환
	}

}
