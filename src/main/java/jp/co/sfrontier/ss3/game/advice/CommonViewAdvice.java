package jp.co.sfrontier.ss3.game.advice;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 各画面共通で表示する情報を管理するクラス
 */
@ControllerAdvice
public class CommonViewAdvice {
	
	/**
	 * 全画面共通で LoginUser を Mdel に追加する
	 */
	@ModelAttribute("loginUser")
	public Object addLoginUser(HttpSession session) {
		return session.getAttribute("loginUser");
	}
}
