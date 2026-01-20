package jp.co.sfrontier.ss3.game.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sfrontier.ss3.game.common.Direction;
import jp.co.sfrontier.ss3.game.service.lookoverthere.LookOverTherePlayService;
import jp.co.sfrontier.ss3.game.service.lookoverthere.value.LookOverThereResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 「あっちむいてほい」のリクエストを受け取るためのコントローラークラス<br>
 * <br>
 */
@Slf4j
@Controller
@RequestMapping("/look-over-there")
@RequiredArgsConstructor
public class LookOverThereController {

	private final LookOverTherePlayService playService;

	/**
	 * 「あっちむいてほい」の対戦画面を表示する<br>
	 * <br>
	 * @return 「あっちむいてほい」の対戦画面
	 */
	@GetMapping
	public String show() {
		log.info("あっちむいてほい対戦画面を表示");
		return "lookoverthere/play";
	}

	/**
	 * 「あっちむいてほい」を1回プレイ後、結果を保存したうえで結果画面を表示する<br>
	 * <br>
	 * @param attackerDirection アタッカーが選択した方向
	 * @param session セッション
	 * @param model 結果画面に表示する情報を格納するモデル
	 * @return 結果画面
	 */
	@PostMapping("/play")
	public String play(
			@RequestParam("direction") Integer attackerDirection,
			HttpSession session,
			Model model) {

		// セッションでアタッカー ID を受け取る
		// Long attackerId = (Long) session.getAttribute("playerId");

		LookOverThereResult result = playService.play(Direction.get(attackerDirection));

		log.info(
				"結果 resultCode={}, attackerDirection={}, defenderDirection={}",
				result.getResultCode(),
				result.getAttackerDirection(),
				result.getDefenderDirection());

		model.addAttribute("result", result);

		return "lookoverthere/result";
	}
}
