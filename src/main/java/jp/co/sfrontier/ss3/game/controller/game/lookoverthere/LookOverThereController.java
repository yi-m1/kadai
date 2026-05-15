package jp.co.sfrontier.ss3.game.controller.game.lookoverthere;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sfrontier.ss3.game.common.Direction;
import jp.co.sfrontier.ss3.game.service.game.lookoverthere.LookOverThereService;
import jp.co.sfrontier.ss3.game.service.game.lookoverthere.LookOverThereResult;
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

	private final LookOverThereService playService;

	/**
	 * 「あっちむいてほい」の対戦画面を表示する<br>
	 * <br>
	 * @return 「あっちむいてほい」の対戦画面
	 */
	@GetMapping
	public String show() {
		log.info("あっちむいてほい対戦画面を表示");
		return "look-over-there/play";
	}

	/**
	 *  * 「あっちむいてほい」を1回プレイ後、結果を生成したうえで結果画面へリダイレクトする<br>
	 * <br>
	 * @param attackerDirection アタッカーの方向
	 * @param redirectAttributes フラッシュスコープで保持する対戦結果の情報
	 * @return 対戦結果画面へのリダイレクト
	 */
	@PostMapping("/play")
	public String play(
			@RequestParam("direction") Integer attackerDirection,
			RedirectAttributes redirectAttributes) {

		try {
			LookOverThereResult result = playService.play(Direction.get(attackerDirection));

			redirectAttributes.addFlashAttribute("result", result);

			log.info("対戦結果 resultCode={}, attacker={}, defender={}",
					result.getResultCode(),
					attackerDirection,
					result.getDefenderDirection());

			return "redirect:/look-over-there/result";

		} catch (IllegalArgumentException e) {
			log.warn("不正な入力", e);
			redirectAttributes.addFlashAttribute("errorMessage", "不正な入力です");
			return "redirect:/error";

		} catch (Exception e) {
			log.error("予期しないエラー", e);
			redirectAttributes.addFlashAttribute("errorMessage", "システムエラーが発生しました");
			return "redirect:/error";
		}
	}

	/**
	 * 対戦結果画面を表示する<br>
	 * <br>
	 * @param model 対戦結果の情報のモデル
	 * @return 対戦結果画面
	 */
	@GetMapping("/result")
	public String result(Model model) {

		if (!model.containsAttribute("result")) {
			throw new IllegalStateException("不正な操作が行われました");
		}

		return "look-over-there/result";
	}

}
