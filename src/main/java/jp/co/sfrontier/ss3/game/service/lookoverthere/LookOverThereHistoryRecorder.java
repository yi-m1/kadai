/**
 * 
 */
package jp.co.sfrontier.ss3.game.service.lookoverthere;

import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.game.common.Direction;
import jp.co.sfrontier.ss3.game.model.GameType;
import jp.co.sfrontier.ss3.game.repository.core.ResultHistoryRepository;
import jp.co.sfrontier.ss3.game.service.core.AbstractHistoryRecorder;
import jp.co.sfrontier.ss3.game.service.core.BattleResult;
import jp.co.sfrontier.ss3.game.value.Player;

/**
 * あっちむいてほい用の対戦履歴を作成するクラス<br>
 * <br>
 */
@Service
public class LookOverThereHistoryRecorder extends AbstractHistoryRecorder<Direction, BattleResult> {

	public LookOverThereHistoryRecorder(ResultHistoryRepository resultHistoryRepository) {
		super(resultHistoryRepository);
	}

	@Override
	protected GameType getGameType() {

		return GameType.LOOKOVERTHERE;
	}

	@Override
	protected String getUserChoice(Player<Direction> player) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
