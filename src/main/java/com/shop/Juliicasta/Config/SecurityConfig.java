package com.shop.Juliicasta.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shop.Juliicasta.Service.AdminDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AdminDetailService adminDetailService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/user/**", "/CSS/**", "/Images/**", "/JS/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/admin/Juliicasta/Manager/login").permitAll()
		.defaultSuccessUrl("/admin/Juliicasta/Manager/index").failureUrl("/admin/Juliicasta/Manager/login?error=true")
		.loginProcessingUrl("/j_spring_security_check")
		.and()
		.logout().permitAll().logoutUrl("/admin/Juliicasta/Manager/logout");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(adminDetailService).passwordEncoder(passwordEncoder());
	}
}
