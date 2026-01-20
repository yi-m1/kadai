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
	 * 不正な入力値によって発生した例外を扱う<br>
	 * <br>
	 * ユーザー操作に起因するため、入力エラー画面へ遷移する
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public String handleIllegalArgument(IllegalArgumentException e, Model model) {

		log.warn("Invalid argument", e);
		model.addAttribute("message", "不正な入力です。");

		return "error/user-error";
	}

	/**
	 * 不正な画面遷移によって発生した例外を扱う<br>
	 * <br>
	 * ユーザー操作または遷移順序の誤りと判断し、入力エラー画面へ遷移する
	 */
	@ExceptionHandler(IllegalStateException.class)
	public String handleIllegalState(IllegalStateException ex, Model model) {

		log.warn("不正な画面遷移", ex);
		model.addAttribute("message", ex.getMessage());

		return "error/user-error";
	}

	/**
	 * データベースアクセス時に発生した例外を扱う<br>
	 * <br>
	 * システム側の障害であるため、システムエラー画面へ遷移する
	 */
	@ExceptionHandler(org.springframework.dao.DataAccessException.class)
	public String handleDbException(Exception e, Model model) {

		log.error("Database error", e);
		model.addAttribute("message", "データベースエラーが発生しました。");

		return "error/system-error";
	}

	/**
	 * 上記で捕捉されなかったすべての例外を扱う<br>
	 * <br>
	 * 画面には汎用的なエラーメッセージのみを表示する
	 */
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, Model model) {

		// ログ（開発者向け）
		log.error("予期しないエラーが発生しました。", e);

		// 画面表示用メッセージ
		model.addAttribute("message", "予期しないエラーが発生しました。");

		return "error/error";
	}

}
