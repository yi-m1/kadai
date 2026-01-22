package jp.co.sfrontier.ss3.game.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jp.co.sfrontier.ss3.game.service.login.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig {

	private final UserDetailsServiceImpl userDetailsService;

	public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	/**
	 *
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authz -> authz.antMatchers("/login", "/register", "/changePassword").permitAll()
				.anyRequest().authenticated())
				.formLogin(login -> login.loginPage("/login").loginProcessingUrl("/login")
						.usernameParameter("userNameOrMailAddress").passwordParameter("password")
						.defaultSuccessUrl("/menu", true).failureUrl("/login?error").permitAll())
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login"));

		return http.build();
	}

	/**
	 * BCryptPasswordEncoderはデフォルトで10という強度値を使用する。<br>
	 * より高いセキュリティが必要な場合は、コンストラクタで強度を指定できる(今回はデフォルトで使用)。<br>
	 *
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
