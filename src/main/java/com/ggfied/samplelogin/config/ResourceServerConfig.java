package com.ggfied.samplelogin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private ResourceServerTokenServices tokenServices;

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Autowired
	private LoginSuccessHandler loginSucccessHandler;

	@Autowired
	private LoginFailureHandler loginFailureHandler;
	
	private final String resourceIds = "testjwtresourceid";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenServices(tokenServices);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		//		http
		//	    .csrf().disable()
		//		.exceptionHandling();
		//		.authenticationEntryPoint(this.restAuthenticationEntryPoint)
		//		.and()
		//		.authorizeRequests()
		//		.antMatchers("/api/foos").authenticated()
		//		.antMatchers("/api/admin/**").hasRole("ADMIN")
		//		.and()
		//		.formLogin()
		//		.successHandler(this.loginSucccessHandler)
		//		.failureHandler(this.loginFailureHandler)
		//		.and()
		//		.logout();
		http
		.requestMatchers()
		.and()
		.authorizeRequests()
		.antMatchers("/actuator/**", "/api-docs/**").permitAll()
		.antMatchers("/api/**" ).authenticated()
		.antMatchers("/api/admin/**").hasRole("ADMIN");
	}

}
