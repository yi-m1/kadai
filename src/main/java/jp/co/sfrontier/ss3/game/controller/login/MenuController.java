package jp.co.sfrontier.ss3.game.controller.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * メニュー画面を表示するコントローラークラス
 */
@Controller
public class MenuController {

	@GetMapping("/menu")
	public String showMenu() {

		// 現在ログインしているユーザ名を取得する
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		// ログインしたユーザが ROLE_ADMIN を持っているか判定する
		boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

		if (isAdmin) {
			return "login/adminmenu";
		} else {
			return "login/menu";
		}
	}
}