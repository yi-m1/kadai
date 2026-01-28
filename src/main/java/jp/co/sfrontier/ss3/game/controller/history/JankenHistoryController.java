package jp.co.sfrontier.ss3.game.controller.history;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sfrontier.ss3.game.model.ResultHistory;
import jp.co.sfrontier.ss3.game.model.UserInfo;
import jp.co.sfrontier.ss3.game.service.history.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class JankenHistoryController {

    private final HistoryService historyService;

    @GetMapping("/history")
    public String showHistory(HttpSession session, Model model) {

        // セッションからログインユーザー情報を取得
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null) {
            log.warn("セッションにユーザー情報が存在しません");
            return "redirect:/login";
        }

        // 履歴取得
        List<ResultHistory> resultHistory = historyService.getHistory(userInfo.getUserId());

        // モデルにセット
        model.addAttribute("history", resultHistory);

        log.debug("履歴件数={}", resultHistory.size());

        return "history";
    }
}
