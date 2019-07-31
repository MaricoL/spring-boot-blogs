package com.waylau.helloworld.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security 配置类.
*/
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


		/**
		 * 自定义配置
		 */
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/css/**", "/js/**", "/fonts/**", "/index").permitAll() // 都可以访问
					.antMatchers("/admins/**").hasRole("ADMIN") // 需要相应的角色才能访问
					.and()
					.formLogin()   //基于 Form 表单登录验证
					.loginPage("/login").failureUrl("/login-error"); // 自定义登录界面

		}

		/**
		 * 认证信息管理
		 * @param auth
		 * @throws Exception
		 */
		@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(new passwordEncoder())
				.withUser("linzeli")
				.password("1234")
				.roles("ADMIN");
	}
}
