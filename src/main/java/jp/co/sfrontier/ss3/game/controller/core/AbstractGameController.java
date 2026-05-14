/**
 * 
 */
package jp.co.sfrontier.ss3.game.controller.core;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jp.co.sfrontier.ss3.game.service.login.LoginUserDetails;
import lombok.extern.slf4j.Slf4j;

/**
 * ゲーム系 Controller の共通処理をまとめた基底クラス<br>
 * <br>
 */
@Slf4j
public abstract class AbstractGameController{

	/**
	 * ログインユーザを取得する
	 */
	protected Integer getLoginUser() {

		// Spring Security が保持している認証情報を取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		
		LoginUserDetails user = (LoginUserDetails) auth.getPrincipal();

		return user.getUser().getUserId().intValue();
	}

	/**
	 * 正常レスポンスを生成する
	 */
	protected ResponseEntity<Map<String, Object>> ok(Map<String, Object> body) {

		body.put("status", "OK");

		return ResponseEntity.ok(body);
	}

	/**
	 * エラーレスポンスを生成する
	 */
	protected ResponseEntity<Map<String, Object>> error(Exception e) {

		log.error("ゲーム実行中にエラー", e);

		Map<String, Object> body = new HashMap<>();

		body.put("status", "ERROR");

		body.put("message", "システムエラーが発生しました");

		return ResponseEntity.internalServerError().body(body);

	}

}
