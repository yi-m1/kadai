/**
 * 
 */
package jp.co.sfrontier.ss3.game.service.game.janken;

import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.game.common.GameType;
import jp.co.sfrontier.ss3.game.common.model.Player;
import jp.co.sfrontier.ss3.game.repository.core.ResultHistoryRepository;
import jp.co.sfrontier.ss3.game.service.game.core.AbstractHistoryRecorder;
import jp.co.sfrontier.ss3.game.service.game.core.BattleResult;
import jp.co.sfrontier.ss3.game.service.game.janken.value.Hand;

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
