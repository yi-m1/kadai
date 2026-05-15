package jp.co.sfrontier.ss3.game.dto.history;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * 対戦履歴のモデルクラス
 */
public class ResultHistoryResponse {

	private String userName;
	private String executeDatetime;
	private String opponent;
	private String result;

}
