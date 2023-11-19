package kr.co.sboard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {

	

	@Bean
	public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
		
		http
				// 사이트 위변조 방지 비활성
				.csrf(CsrfConfigurer::disable) // 메서드 참조 연산자로 람다식을 간결하게 표현
				// 폼 로그인 설정
				.formLogin(config -> config.loginPage("/user/login")
						.defaultSuccessUrl("/",true)
						.failureUrl("/user/login?success=100")
						/*.successHandler(new AuthenticationSuccessHandler() {
							@Override
							public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
								response.sendRedirect("/");
							}
						})
						*/

						.usernameParameter("uid")
						.passwordParameter("pass"))

			//로그아웃 설정
				 .logout(config ->config.logoutUrl("/user/logout")
						 				.invalidateHttpSession(true)
						 				.clearAuthentication(true)
						 				.logoutSuccessUrl("/user/login?success=200"))
			//인가 권한 설정
			.authorizeHttpRequests(AuthorizeRequests ->AuthorizeRequests
														.requestMatchers("/admin/**").hasRole("ADMIN")
														.requestMatchers("/article/**").hasAnyRole("ADMIN","MANAGER","USER")
														.requestMatchers("/").permitAll()
														.requestMatchers("/user/**").permitAll()
														.requestMatchers("/user/login").permitAll()
														.requestMatchers("/vendor/**", "/js/**", "/dist/**", "/data/**", "/less/**", "/profile").permitAll()
														);

		return http.build();
				//개발할 때는 crsf를 해제시켜 놓는다.
				//API서버이므로 세션 인증 방식으로 처리를 하지 않으므로, Httpbasic도 disable시켜준다.
				//로그인을 할 때 데이터는 JSON으로 넘어온다.	
			}
	
	
			@Bean
			public PasswordEncoder passwordEncoder() {
				return new BCryptPasswordEncoder();
			}
	
	@Bean
	public AuthenticationManager authenticationManager (AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}


}
