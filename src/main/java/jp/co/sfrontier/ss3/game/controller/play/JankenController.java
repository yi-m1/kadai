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
import jp.co.sfrontier.ss3.game.model.UserInfoModel;
import jp.co.sfrontier.ss3.game.service.janken.JankenResult;
import jp.co.sfrontier.ss3.game.service.janken.JankenService;
import jp.co.sfrontier.ss3.game.value.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * じゃんけんゲームコントローラー（最終版）
 */
@Slf4j
@Controller
@RequestMapping("/game")
@RequiredArgsConstructor
public class JankenController {

    private static final String SESSION_LOGIN_USER = "loginUser";

    private final JankenService jankenService;

    /**
     * 対戦画面を表示
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
    public ResponseEntity<Map<String, Object>> play(
            @RequestParam("hand") String handParam,
            HttpSession session) {

        Map<String, Object> response = new HashMap<>();

        // ログインユーザー取得
        UserInfoModel loginUser = (UserInfoModel) session.getAttribute(SESSION_LOGIN_USER);

        if (loginUser == null) {
            log.warn("未ログイン状態でのアクセス");
            response.put("status", "UNAUTHORIZED");
            return ResponseEntity.status(401).body(response);
        }

        // 手のバリデーション
        Hand hand = Hand.get(handParam);
        if (hand == null) {
            log.warn("不正な手の入力: {}", handParam);
            response.put("status", "ERROR");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            Player player = new Player(
                    loginUser.getUserId().intValue(),
                    hand);

            // 修正ポイント：JankenResult を取得
            JankenResult jankenResult = jankenService.fight(player);

            response.put("status", "OK");
            response.put("result", jankenResult.getResult()); // 勝敗結果
            response.put("cpuHand", jankenResult.getCpuHand().name()); // CPUの手

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("じゃんけん処理中にエラー", e);
            response.put("status", "ERROR");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
