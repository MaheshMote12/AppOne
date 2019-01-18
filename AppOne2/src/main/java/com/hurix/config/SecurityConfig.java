package com.hurix.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.hurix.auth.CustomAuthenticationProvider;
import com.hurix.auth.JwtConfig;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider authProvider;

	protected void  configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig()), UsernamePasswordAuthenticationFilter.class).
		authorizeRequests()
				.antMatchers("/")
				.permitAll().antMatchers("/login").permitAll()
				.anyRequest().authenticated()
				.and().csrf().disable()
				.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error=true")
				.defaultSuccessUrl("/uploadFile")
				.usernameParameter("userName")
				.passwordParameter("password")

				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout=true")
				.and()
				.exceptionHandling()
				.accessDeniedPage("/AccessDenied");
	}
	
	
	@Bean
  	public JwtConfig jwtConfig() {
    	   return new JwtConfig();
}
}