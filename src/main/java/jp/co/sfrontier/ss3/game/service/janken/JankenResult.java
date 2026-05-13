package jp.co.sfrontier.ss3.game.service.janken;

import jp.co.sfrontier.ss3.game.common.Hand;
import jp.co.sfrontier.ss3.game.common.ResultCode;
import jp.co.sfrontier.ss3.game.service.core.BattleResult;
import lombok.Getter;

/**
 * じゃんけん結果を表すクラス
 */
@Getter
public class JankenResult {

	private final BattleResult battleResult;

	private final Hand cpuHand;

	public JankenResult(BattleResult battleResult, Hand cpuHand) {
		this.battleResult = battleResult;
		this.cpuHand = cpuHand;
	}

	public ResultCode getResultCode() {
		return battleResult.getResultCode();
	}
}