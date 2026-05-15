package jp.co.sfrontier.ss3.game.dto.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * じゃんけん結果のモデルクラス
 */
@Getter
@RequiredArgsConstructor
public class JankenResultResponse {
	private int userId;
	private String userChoice;
	private String aiChoice;
	private String resultMessage;

}
