package learn.springboot.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	/* 
	@Bean
	InMemoryUserDetailsManager userDetailsManager() {
		
		UserDetails suman= User.builder()
				.username("suman")
				.password("{noop}test123")
				.roles("employee")
				.build();
		
		UserDetails elakiya= User.builder()
				.username("elakiya")
				.password("{noop}test123")
				.roles("employee", "lead")
				.build();
		
		UserDetails srini= User.builder()
				.username("srini")
				.password("{noop}test123")
				.roles("employee", "manager")
				.build();
		
		return new InMemoryUserDetailsManager(suman, elakiya, srini);
	}
	*/
	
	@Bean
	UserDetailsManager userDetailsManager(DataSource dataSource) {
		
		return new JdbcUserDetailsManager(dataSource);
	}
	
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(configurer -> configurer
			.requestMatchers(HttpMethod.GET, "/api/employee", "/api/employee/**").hasRole("employee")
			.requestMatchers(HttpMethod.POST, "/api/employee").hasAnyRole("lead", "manager")
			.requestMatchers(HttpMethod.PUT, "/api/employee/**").hasAnyRole("lead", "manager")
			.requestMatchers(HttpMethod.DELETE, "/api/employee/**").hasRole("manager"));
		
		http.httpBasic(Customizer.withDefaults());
		http.csrf(csrf -> csrf.disable());
		
		return http.build();
	}
}
