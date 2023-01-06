package com.wow.api.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	SystemPasswordEncoder systemPasswordEncoder;

	@Autowired
    public void configureSystem(AuthenticationManagerBuilder auth) {
        //web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
		System.out.println("configureSystem");
    	auth.authenticationProvider(authProvider());
    	
    }
    
    public AuthenticationProvider authProvider() {
    	System.out.println("authProvider");
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(systemPasswordEncoder);
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    
   
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       
    	 http.headers().frameOptions().disable();			
			http.csrf().disable();
			http.authorizeRequests()
				.antMatchers(
						"/css/**", "/js/**", "/images/**", "/lib/**", "/icons/**","/scss/**","/vendor/**","/login"
				).permitAll().anyRequest().authenticated();
			
			// Submit URL login page
			http.authorizeRequests()
				.and()
					.formLogin()
						.loginProcessingUrl("/login")
						.loginPage("/login")
						.defaultSuccessUrl("/index", true)
						.failureUrl("/login?error=true")
						.usernameParameter("username")
						.passwordParameter("password")
			 // config Logout Page.
				.and()
					.logout().logoutUrl("/logout").logoutSuccessUrl("/");
			
			// config Remember Me.
			/*
			http.authorizeRequests().and()
				.rememberMe().tokenRepository(this.persistentTokenRepository())
				.tokenValiditySeconds(1 * 24 * 60 * 60);
		
    	*/
        return http.build();
    }
    
    @Bean
	public PersistentTokenRepository persistentTokenRepository() {
	    InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
	    return memory;
	}
    
    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	System.out.println("configure");
    	http.csrf().disable();
        http.headers().frameOptions().disable();
        
        http.authorizeRequests()
        //image 폴더를 login 없이 허용
        .antMatchers("/images/**").permitAll()
        //css 폴더를 login 없이 허용
        .antMatchers("/css/**").permitAll()
        //js 폴더를 login 없이 허용
        .antMatchers("/js/**").permitAll()
        .anyRequest().authenticated()
        .and()
          .formLogin()                           // 사용자 정의 로그인 페이지, default: /login
          //.loginProcessingUrl("/action") //  로그인 Form Action Url, default: /login
          //.loginPage("/login")
          //.defaultSuccessUrl("/", true)     // 로그인 성공 후 이동 페이지
          //.failureUrl("/login?error=true")  // 로그인 실패 후 이동 페이지
          //.usernameParameter("username")    // 아이디 파라미터명 설정, default: username
          //.passwordParameter("password")    // 패스워드 파라미터명 설정, default: password
        .defaultSuccessUrl("/")
        .permitAll()
        .and()
        .logout()
        .permitAll();
    }
    */
}
