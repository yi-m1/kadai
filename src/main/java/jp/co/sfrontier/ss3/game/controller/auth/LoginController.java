package jp.co.sfrontier.ss3.game.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sfrontier.ss3.game.dto.auth.LoginForm;

/**
 * ログイン処理を行うコントローラークラス
 */
@Controller
public class LoginController {

	@GetMapping("/login")
	public String showLoginPage(@RequestParam(value = "error", required = false) String error,
			@ModelAttribute("loginForm") LoginForm form, Model model) {

		if (error != null) {
			model.addAttribute("loginError", "ログインできませんでした。入力内容が間違っている可能性があります。");
		}

		return "login/login";
	}
}
