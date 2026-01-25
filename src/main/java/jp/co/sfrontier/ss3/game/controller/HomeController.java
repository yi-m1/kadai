package jp.co.sfrontier.ss3.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * アプリのトップページ表示を制御するコントローラクラス<br>
 * <br>
 */
@Controller
public class HomeController {

	@GetMapping("/")
	public String show() {
		return "home";
	}
}
