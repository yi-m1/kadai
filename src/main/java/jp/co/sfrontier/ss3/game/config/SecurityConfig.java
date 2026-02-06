package jp.co.sfrontier.ss3.game.config;

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

/**
 * Spring Security を用いてログイン、権限制御、パスワード暗号化の設定を行うクラス
 */
@Configuration
public class SecurityConfig {

	private final UserDetailsServiceImpl userDetailsService;

	public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	/**
	 * Spring Security のセキュリティ設定を定義する。<br>
	 * <br>
	 * 管理ユーザはユーザ全員の対戦履歴を表示でき、一般ユーザは自分の履歴のみ表示できる。<br>
	 *
	 * @param http ttpSecurity 設定用オブジェクト
	 * @return 構築された SecurityFilterChain
	 * @throws Exception セキュリティ設定時に発生する例外
	 */
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authz -> authz
				// 誰でもアクセス可能
				// ログイン画面、登録画面、パスワード変更画面、静的リソース
				.antMatchers("/login", "/register", "/changePassword", "/css/**", "/js/**", "/images/**").permitAll()

				// 管理ユーザのみアクセス可能(ユーザ管理画面)
				.antMatchers("/user/**").hasRole("ADMIN")
				// 一般ユーザのみアクセス可能(じゃんけん画面)
				.antMatchers("/game/**").hasRole("USER")
				
				.antMatchers("/look-over-there/**").hasRole("USER")

				// 管理ユーザも一般ユーザもアクセス可能(履歴画面)
				.antMatchers("/history").hasAnyRole("USER", "ADMIN")

				// ログインしていればアクセス可能
				.anyRequest().authenticated())
				.formLogin(login -> login
						.loginPage("/login")
						.loginProcessingUrl("/login")
						.usernameParameter("userNameOrMailAddress")
						.passwordParameter("password")
						.defaultSuccessUrl("/menu", true)
						.failureUrl("/login?error")
						.permitAll())
				.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login"));

		return http.build();
	}

	/**
	 * BCryptPasswordEncoderはデフォルトで10という強度値を使用する。<br>
	 * <br>
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
