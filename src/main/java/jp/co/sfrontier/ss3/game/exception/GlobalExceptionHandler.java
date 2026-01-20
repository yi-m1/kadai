package jp.co.sfrontier.ss3.game.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * 全コントローラ共通で例外をキャッチするクラス<br>
 * <br>
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 不正な入力による例外を扱う
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public String handleIllegalArgument(IllegalArgumentException ex, Model model) {

		log.warn("Invalid argument", ex);
		model.addAttribute("message", "不正な入力です。");

		return "error/user-error";
	}
	
	/**
	 * データベースエラーを扱う
	 */
	@ExceptionHandler(org.springframework.dao.DataAccessException.class)
	public String handleDbException(Exception ex, Model model) {

	    log.error("Database error", ex);
	    model.addAttribute("message", "データベースエラーが発生しました。");

	    return "error/system-error";
	}


	/**
	 * 予期しない例外扱う
	 */
	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex, Model model) {

		// ログ（開発者向け）
		log.error("予期しないエラーが発生しました。", ex);

		// 画面表示用メッセージ
		model.addAttribute("message", "予期しないエラーが発生しました。");

		return "error/system-error";
	}

}
