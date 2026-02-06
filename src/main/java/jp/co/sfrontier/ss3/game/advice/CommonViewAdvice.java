package jp.co.sfrontier.ss3.game.advice;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jp.co.sfrontier.ss3.game.entity.UserInformationTbl;
import jp.co.sfrontier.ss3.game.service.login.LoginUserDetails;

/**
 * 各画面共通で表示する情報を管理するクラス
 */
@ControllerAdvice
public class CommonViewAdvice {

	/**
	 * 全画面共通で LoginUser を Model に追加する
	 */
	@ModelAttribute("loginUser")
	public UserInformationTbl loginUser() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		System.out.println("auth = " + auth);
		System.out.println("principal = " + auth.getPrincipal());

		if (auth == null
				|| !auth.isAuthenticated()
				|| "anonymousUser".equals(auth.getPrincipal())) {
			return null;
		}

		LoginUserDetails userDetails = (LoginUserDetails) auth.getPrincipal();

		return userDetails.getUser();
	}
}
