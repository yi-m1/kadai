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

		if (auth == null || !auth.isAuthenticated()) {
			return null;
		}

		Object principal = auth.getPrincipal();

		if (!(principal instanceof LoginUserDetails)) {
			return null;
		}

		LoginUserDetails userDetails = (LoginUserDetails) principal;
		return userDetails.getUser();
	}
}
