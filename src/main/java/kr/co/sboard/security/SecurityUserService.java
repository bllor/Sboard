package kr.co.sboard.security;

import kr.co.sboard.entity.UserEntity;
import kr.co.sboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class SecurityUserService implements UserDetailsService  {
//DB에 ID, 비번이 맞는지 점검
//맞으면 USERDetails라는 클래스를 만듬(=MyUserDetails.class)

	@Autowired
	private UserRepository repo;
	//
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	//DB로 질의를 보내는 메서드	
	//아이디만 있는데 비밀번호까지 조회를 어떻게 할까?
	//loadUserByUsername - UserDetailsService이 실행되기 전 다른 단계 AuthenticationProvider에서 비밀번호 검사가 끝나고 넘어온다.
	
	//패스워드에 대한 검사는 이전 컴포넌트(AuthenticationProvider)에서 처리되어 사용자 아이디만 넘어옴	
		
		UserEntity user = repo.findById(username)
						.orElseThrow(()-> new UsernameNotFoundException(username+" NotFound"));
		
		//사용자 인증객체 생성(세션에 저장)
		UserDetails userDetails =  MyUserDetails.builder()
												.user(user)
												.build();
		
		return userDetails;
		
	}
}
