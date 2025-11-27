package jp.co.sfrontier.ss3.janken_game.controller.play;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.Hand;
import jp.co.sfrontier.ss3.janken_game.controller.util.ServletUtils;
import jp.co.sfrontier.ss3.janken_game.model.UserInfo;
import jp.co.sfrontier.ss3.janken_game.service.janken.JankenServiceTomari;
import value.Player;

/**
 * じゃんけんゲームをするためのSpringコントローラークラス
 */
@Controller
@RequestMapping("/game")
public class GameController {

    private static final Logger logger = LogManager.getLogger(GameController.class);

    private final JankenServiceTomari jankenService;

    // SpringのDIでサービスを注入
    public GameController(JankenServiceTomari jankenService) {
        this.jankenService = jankenService;
    }

    /**
     * GET: JSPを表示
     */
    @GetMapping("/play")
    public String showPlayPage() {
        logger.debug("Game Start");
        // JSPを返す（/WEB-INF/jsp/game/play.jsp）
        return "game/play";
    }

    /**
     * POST: JSONを返す
     */
    @PostMapping(value = "/play", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> play(@RequestParam String hand, HttpServletRequest request) {
        logger.debug("Game Play");

        UserInfo userInfo = ServletUtils.getUserInfo(request);

        Hand h = Hand.get(hand);
        if (h == null) {
            logger.debug("input error{hand={}}", hand);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "ERROR");
            return ResponseEntity.badRequest().body(jsonResponse.toString());
        }

        Player player = new Player(userInfo.getUserId(), h);
        logger.debug("call jankenService");

        try {
            int result = jankenService.fight(player);
            logger.debug("jankenResult{result={}}", result);

            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "OK");
            jsonResponse.put("cpuHand", getCpuHand(h, result));
            jsonResponse.put("result", result);

            return ResponseEntity.ok(jsonResponse.toString());

        } catch (Exception e) {
            logger.error("じゃんけんの処理中にエラーが発生しました", e);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "ERROR");
            return ResponseEntity.internalServerError().body(jsonResponse.toString());
        }
    }

    /**
     * CPUの手を決定するメソッド
     */
    private String getCpuHand(Hand hand, int result) {
        Hand cpuHand = hand;
        if (result > 0) {
            switch (hand) {
                case ROCK: cpuHand = Hand.SCISSORS; break;
                case SCISSORS: cpuHand = Hand.PAPER; break;
                case PAPER: cpuHand = Hand.ROCK; break;
            }
        } else if (result < 0) {
            switch (hand) {
                case ROCK: cpuHand = Hand.PAPER; break;
                case SCISSORS: cpuHand = Hand.ROCK; break;
                case PAPER: cpuHand = Hand.SCISSORS; break;
            }
        }
        return cpuHand.name();
    }
}