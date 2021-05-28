package org.swss.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@Order(1)
public class ActuatorConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService localUserDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.userDetailsService(localUserDetailService)
			.authorizeRequests()
			.antMatchers("/actuator/**").hasAnyRole("ACTUATOR_SUPERVISOR")
			.and().httpBasic()
			.and().csrf().disable()
			.headers().disable();
			
	}
	
}
