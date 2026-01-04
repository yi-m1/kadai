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

/**
 * 「あっちむいてほい」のリクエストを受け取るためのコントローラークラス<br>
 * <br>
 */
@Controller
@RequestMapping("/look-over-there")
public class LookOverThereController {

	private final LookOverTherePlayService playService;
	private final MatchResultService matchResultService;

	public LookOverThereController(
			LookOverTherePlayService playService,
			MatchResultService matchResultService) {
		this.playService = playService;
		this.matchResultService = matchResultService;
	}

	@GetMapping
	public String show(HttpSession session) {

		if (session.getAttribute("playerId") == null) {
			// 仮ユーザーID（ログイン実装後に削除）
			session.setAttribute("playerId", 1L);
		}

		return "lookoverthere/play";
	}

	@PostMapping("/play")
	public String play(
			@RequestParam("direction") Integer attackerDirection,
			HttpSession session,
			Model model) {

		Long attackerId = (Long) session.getAttribute("playerId");
		Long defenderId = 0L; // CPU
		Long gameId = LookOverTherePlayService.GAME_ID;

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
