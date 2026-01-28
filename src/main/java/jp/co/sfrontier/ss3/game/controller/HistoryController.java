package jp.co.sfrontier.ss3.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sfrontier.ss3.game.service.lookoverthere.LookOverTherePlayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 対戦履歴画面の表示を制御するクラス<br>
 * <br>
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class HistoryController {
	private final LookOverTherePlayService playService;

	/**
	 * 対戦履歴を表示する<br>
	 * <br>
	 * @param model 対戦履歴画面に渡すモデル
	 * @return 対戦履歴画面
	 */
	@GetMapping("/history")
	public String history(Model model) {
		// ログイン未実装のため固定 ID
		Long playerId = 1L;

		log.info("対戦履歴表示 playerId={}", playerId);

		model.addAttribute("histories", playService.getHistory(playerId));

		return "history";
	}
}