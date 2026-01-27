package jp.co.sfrontier.ss3.game.controller.history;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sfrontier.ss3.game.dto.MatchResultRequest;
import jp.co.sfrontier.ss3.game.model.GameType;
import jp.co.sfrontier.ss3.game.service.history.HistoryService;

/**
 * 対戦履歴一覧画面に関するリクエストを受け付ける Controller クラス。
 */
@Controller
public class HistoryController {

 @Autowired
 private HistoryService historyService;

 /**
  * 対戦履歴一覧画面を表示する。
  *
  * <p>
  * URLパラメータ tab に応じて表示対象の履歴を切り替える。
  * </p>
  *
  * <p>
  * 指定された tab の値をもとに Service を呼び分け、 取得した履歴一覧を画面表示用として Model に設定する。
  * </p>
  *
  * @param tab   表示する履歴種別を表すタブ識別子（defaultValueでデフォルトはallとする）
  * @param model 画面表示用のモデル
  * @return 対戦履歴画面名(history.html)
  */
 @GetMapping("/history")
 public String history(@RequestParam(name = "tab", defaultValue = "all") String tab, Model model) {

  List<MatchResultRequest> resultHistoryRequest;

  // tab によって Service を呼び分ける
  switch (tab) {
  case "janken":
   resultHistoryRequest = historyService.findByGameType(GameType.JANKEN);
   break;
  case "acchi":
   resultHistoryRequest = historyService.findByGameType(GameType.ACCHI);
   break;
  default:
   resultHistoryRequest = historyService.findAll();
  }
  model.addAttribute("rows", resultHistoryRequest);
  model.addAttribute("tab", tab);

  return "history";
 }
}