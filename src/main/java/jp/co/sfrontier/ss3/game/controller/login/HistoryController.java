package jp.co.sfrontier.ss3.game.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 権限ごとに履歴画面が表示できるか確認するためのクラスです。後で消します。
 */
// TODO 後でこのクラスを消す。htmlも忘れずに消す。
@Controller
public class HistoryController {

	@GetMapping("/history")
	public String myHistory() {
		return "login/history";
	}

	@GetMapping("/history/all")
	public String allHistory() {
		return "login/historyAll";
	}
}