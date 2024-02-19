package com.WishList_Management.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;



import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class Config {

	  @Autowired
	    private JwtAuthenticationEntryPoint point;
	  @Autowired
	    private JwtAuthenticationFilter filter;
	  @Autowired
	private  UserDetailsService userDetailsService;
	  @Autowired
	private PasswordEncoder passwordEncoder ;
		@Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	        http.csrf(csrf -> csrf.disable())
	                .cors(cors->cors.configurationSource(request -> {
	                	CorsConfiguration configuration= new CorsConfiguration();
						configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
						configuration.setAllowedMethods(Collections.singletonList("*"));
						configuration.setAllowCredentials(true);
						configuration.setAllowedHeaders(Collections.singletonList("*"));
						configuration.setExposedHeaders(Arrays.asList("Authorization"));
						return configuration;})).
	                authorizeHttpRequests(auth->auth.requestMatchers("/user/**").permitAll()
	                	.anyRequest().authenticated()).
	                exceptionHandling(ex->ex.authenticationEntryPoint(point))
	                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	                
	        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	        return http.build();
	    }
		
		@Bean
		public DaoAuthenticationProvider daoAuthenticationProvider() {
			DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
			provider.setUserDetailsService(userDetailsService);
		
			provider.setPasswordEncoder(passwordEncoder);
			return provider;
		}
}
