/**
 * 
 */
package jp.co.sfrontier.ss3.game.service.game.lookoverthere;

import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.game.common.Direction;
import jp.co.sfrontier.ss3.game.common.GameType;
import jp.co.sfrontier.ss3.game.common.model.Player;
import jp.co.sfrontier.ss3.game.repository.core.ResultHistoryRepository;
import jp.co.sfrontier.ss3.game.service.game.core.AbstractHistoryRecorder;
import jp.co.sfrontier.ss3.game.service.game.core.BattleResult;

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
