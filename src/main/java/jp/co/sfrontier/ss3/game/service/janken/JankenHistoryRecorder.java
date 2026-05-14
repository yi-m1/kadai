/**
 * 
 */
package jp.co.sfrontier.ss3.game.service.janken;

import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.game.common.Hand;
import jp.co.sfrontier.ss3.game.model.GameType;
import jp.co.sfrontier.ss3.game.repository.core.ResultHistoryRepository;
import jp.co.sfrontier.ss3.game.service.core.AbstractHistoryRecorder;
import jp.co.sfrontier.ss3.game.service.core.BattleResult;
import jp.co.sfrontier.ss3.game.value.Player;

/**
 * じゃんけん用の対戦履歴を作成するクラス<br>
 * <br>
 */
@Service
public class JankenHistoryRecorder extends AbstractHistoryRecorder<Hand,BattleResult> {

	public JankenHistoryRecorder(ResultHistoryRepository resultHistoryRepository) {
		super(resultHistoryRepository);
	}

	@Override
	protected GameType getGameType() {
		
		return GameType.JANKEN;
	}

	@Override
	protected String getUserChoice(Player<Hand> player) {
		
		return player.getAction().name();
	}
	
}
