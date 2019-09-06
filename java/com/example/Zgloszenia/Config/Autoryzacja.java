/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package com.example.Zgloszenia.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author siema
 */
/*@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class Autoryzacja extends WebSecurityConfigurerAdapter{
    
    @Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("justyna").password("123").roles("USER")
		.and()
		.withUser("aurora").password("321").roles("USER", "ADMIN");
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/","/lokator/**").permitAll()
		.antMatchers("/dozorca/**").hasRole("ADMIN")
		.antMatchers("/lokator/**").hasRole("USER")
		.anyRequest().authenticated()
		.and().formLogin().defaultSuccessUrl("/")
		.and().logout().logoutSuccessUrl("/");
}
        }
*/