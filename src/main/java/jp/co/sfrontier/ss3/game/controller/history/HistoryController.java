package jp.co.sfrontier.ss3.game.controller.history;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sfrontier.ss3.game.dto.MatchPageResultRequest;
import jp.co.sfrontier.ss3.game.model.GameType;
import jp.co.sfrontier.ss3.game.service.history.HistoryService;
import lombok.RequiredArgsConstructor;

/**
 * 対戦履歴一覧画面に関するリクエストを受け付ける Controller クラス。
 */

/**
 * 対戦履歴一覧画面に関するリクエストを受け付ける Controller クラス。
 *
 * <p>
 * ログインユーザーの対戦履歴を取得し、ページング（20件ずつ）および
 * ゲーム種別タブ（全件／じゃんけん／あっちむいてほい）による
 * 絞り込みを行った結果を画面に表示する。
 * </p>
 *
 * <p>
 * 実際の検索条件の組み立てやページング処理は Service に委譲し、
 * Controller ではリクエストパラメータの受け取りと
 * 画面表示用データの受け渡しのみを担当する。
 * </p>
 */
@Controller
@RequiredArgsConstructor
public class HistoryController {

	
	private final HistoryService historyService;
	
	/**
	 * 対戦履歴一覧画面を表示する。
	 *
	 * <p>
	 * URLパラメータで指定された tab（all / janken / acchi）および page をもとに、
	 * 対象となる履歴をページング（1ページ20件）して取得する。
	 * </p>
	 *
	 * <p>
	 * ページング処理および総件数の取得は Service 側で行い、
	 * Controller では画面表示に必要な情報を Model に詰める。
	 * </p>
	 *
	 * @param tab  表示する履歴種別（all, janken, acchi）
	 * @param page 表示するページ番号（1始まり）
	 * @param model 画面表示用の Model
	 * @return 対戦履歴一覧画面（history.html）
	 */
	@GetMapping("/history")
	public String showHistory(@RequestParam(name = "tab", defaultValue = "all") String tab,
			@RequestParam(name = "page", defaultValue = "1") int page,
			Model model) {

		//1ページあたりの表示件数
		int size = 20;
		//対戦履歴をページングして取得する（全ゲーム種別)
		MatchPageResultRequest result = historyService.findPageAll(page, size);

		switch (tab) {
		case "janken":
			result = historyService.findPageByGameType(GameType.JANKEN, page, size);
			break;
		case "acchi":
			result = historyService.findPageByGameType(GameType.LOOKOVERTHERE, page, size);
			break;
		default:
			// 何もしない（findPageAllのまま）
		}

		model.addAttribute("rows", result.getRows());
		model.addAttribute("tab", tab);	

		// ページング用
		model.addAttribute("page", result.getPage());
		model.addAttribute("size", result.getSize());
		model.addAttribute("totalPages", result.getTotalPages());
		model.addAttribute("totalCount", result.getTotalCount());
		
	    model.addAttribute("activeMenu", "history");

		return "history";
	}
}