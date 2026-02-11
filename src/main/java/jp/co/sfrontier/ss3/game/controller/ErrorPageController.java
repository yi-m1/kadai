package jp.co.sfrontier.ss3.game.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller
public class ErrorPageController {

	@GetMapping("/error")
	public String error(Model model) {

		if (!model.containsAttribute("errorMessage")) {
			model.addAttribute("message", "不明なエラーが発生しました");
		}

		return "error/error";
	}
}
