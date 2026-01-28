package jp.co.sfrontier.ss3.game.controller.play;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.sfrontier.ss3.game.common.Hand;
import jp.co.sfrontier.ss3.game.controller.util.ServletUtils;
import jp.co.sfrontier.ss3.game.model.UserInfo;
import jp.co.sfrontier.ss3.game.service.janken.JankenServiceTomari;
import jp.co.sfrontier.ss3.game.value.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * じゃんけんゲームをSpring MVC化したコントローラー
 */
@Slf4j
@Controller
@RequestMapping("/game")
@RequiredArgsConstructor
public class JankenController {

    private final JankenServiceTomari jankenService;

    /**
     * 対戦画面を表示
     */
    @GetMapping("/play")
    public String show() {
        log.debug("Game Start");
        return "game/play"; // /WEB-INF/jsp/game/play.jsp に相当
    }

    /**
     * プレイヤーの手を受け取り、結果をJSONで返す
     */
    @PostMapping("/play")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> play(
            @RequestParam("hand") String handParam,
            HttpServletRequest request) {

        Map<String, Object> response = new HashMap<>();

        try {
            UserInfo userInfo = ServletUtils.getUserInfo(request);
            Hand hand = Hand.get(handParam);

            if (hand == null) {
                log.debug("input error{hand={}}", handParam);
                response.put("status", "ERROR");
                return ResponseEntity.badRequest().body(response);
            }

            Player player = new Player(userInfo.getUserId(), hand);

            log.debug("call jankenService");
            int result = jankenService.fight(player);
            log.debug("jankenResult{result={}}", result);

            response.put("status", "OK");
            response.put("cpuHand", getCpuHand(hand, result));
            response.put("result", result);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("じゃんけんの処理中にエラーが発生しました", e);
            response.put("status", "ERROR");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    /**
     * CPUの手を計算
     */
    private String getCpuHand(Hand hand, int result) {
        Hand cpuHand = hand;
        if (result > 0) {
            switch (hand) {
                case ROCK -> cpuHand = Hand.SCISSORS;
                case SCISSORS -> cpuHand = Hand.PAPER;
                case PAPER -> cpuHand = Hand.ROCK;
            }
        } else if (result < 0) {
            switch (hand) {
                case ROCK -> cpuHand = Hand.PAPER;
                case SCISSORS -> cpuHand = Hand.ROCK;
                case PAPER -> cpuHand = Hand.SCISSORS;
            }
        }
        return cpuHand.name();
    }
}
