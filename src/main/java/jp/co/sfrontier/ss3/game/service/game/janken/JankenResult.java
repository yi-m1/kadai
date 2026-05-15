package jp.co.sfrontier.ss3.game.service.game.janken;

import jp.co.sfrontier.ss3.game.common.ResultCode;
import jp.co.sfrontier.ss3.game.service.game.core.BattleResult;
import jp.co.sfrontier.ss3.game.service.game.janken.value.Hand;
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