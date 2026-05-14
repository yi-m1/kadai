package jp.co.sfrontier.ss3.game.controller.play;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.sfrontier.ss3.game.common.Hand;
import jp.co.sfrontier.ss3.game.controller.core.AbstractGameController;
import jp.co.sfrontier.ss3.game.service.janken.JankenResult;
import jp.co.sfrontier.ss3.game.service.janken.JankenService;
import jp.co.sfrontier.ss3.game.value.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * じゃんけんゲームコントローラー
 */
@Slf4j
@Controller
@RequestMapping("/game")
@RequiredArgsConstructor
public class JankenController extends AbstractGameController {

	private final JankenService jankenService;

	/**
	 * 対戦画面を表示する
	 */
	@GetMapping("/play")
	public String show() {

		log.debug("Game Start");

		return "game/play";
	}

	/**
	 * プレイヤーの手を受け取り、結果をJSONで返す
	 */
	@PostMapping("/play")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> play(@RequestParam("hand") String handParam, HttpSession session) {

		try {
			// パラメータを enum に変換
			Hand hand = Hand.get(handParam);

			Player player = new Player(getLoginUser(), hand);

			JankenResult jankenResult = jankenService.fight(player);

			Map<String, Object> response = new HashMap<>();

			response.put("result", jankenResult.getResultCode()); // 勝敗結果
			response.put("cpuHand", jankenResult.getCpuHand().name());

			return ok(response);

		} catch (Exception e) {
			return error(e);
		}

	}
}
