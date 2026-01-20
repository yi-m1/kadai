package jp.co.sfrontier.ss3.game.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sfrontier.ss3.game.common.Direction;
import jp.co.sfrontier.ss3.game.service.MatchResultService;
import jp.co.sfrontier.ss3.game.service.lookoverthere.LookOverTherePlayService;
import jp.co.sfrontier.ss3.game.service.lookoverthere.value.LookOverThereResult;
import lombok.RequiredArgsConstructor;

/**
 * 「あっちむいてほい」のリクエストを受け取るためのコントローラークラス<br>
 * <br>
 */
@Controller
@RequestMapping("/look-over-there")
@RequiredArgsConstructor
public class LookOverThereController {

	private final LookOverTherePlayService playService;
	private final MatchResultService matchResultService;
	
	/**
	 * 「あっちむいてほい」の対戦画面を表示する<br>
	 * <br>
	 * @return 「あっちむいてほい」の対戦画面
	 */
	@GetMapping
	public String show() {
		return "lookoverthere/play";
	}

	/**
	 * 「あっちむいてほい」を1回プレイ後、結果を保存したうえで結果画面を表示する<br>
	 * <br>
	 * @param attackerDirection アタッカーが選択した方向
	 * @param session セッション
	 * @param model 結果画面に表示する情報を格のするモデル
	 * @return 結果画面
	 */
	@PostMapping("/play")
	public String play(
			@RequestParam("direction") Integer attackerDirection,
			HttpSession session,
			Model model) {

		Long attackerId = (Long) session.getAttribute("playerId");
		// ディフェンダーは CPU に固定する
		Long defenderId = 0L;
		Long gameId = LookOverTherePlayService.GAME_ID;

		// 不正値を Service に渡さないために、入力値を Direction に変換しておく
		Direction attackerDir = Direction.get(attackerDirection);

		LookOverThereResult result = playService.play(attackerDir);

		matchResultService.save(
				attackerId,
				defenderId,
				result.getResultCode(),
				attackerDir.getVal(),
				result.getDefenderDirection().getVal(),
				gameId);

		model.addAttribute("result", result);

		return "lookoverthere/result";
	}

}
