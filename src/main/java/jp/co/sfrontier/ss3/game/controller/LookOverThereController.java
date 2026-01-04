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
    public String show() {
        return "lookoverthere/play";
    }
    
    @PostMapping("/play")
    public String play(
            @RequestParam("direction") Integer attackerDirection,
            HttpSession session,
            Model model) {

        Long attackerId = (Long) session.getAttribute("playerId");
        if (attackerId == null) {
            attackerId = 1L; // 仮
        }

        Direction attackerDir = Direction.get(attackerDirection);

        LookOverThereResult result =
                playService.play(attackerDir);

        matchResultService.save(
                attackerId,
                0L,
                result.getResultCode(),
                attackerDir.getVal(),
                result.getDefenderDirection().getVal(),
                LookOverTherePlayService.GAME_ID
        );

        model.addAttribute("result", result);
        return "lookoverthere/result";
    }


}

