/**
 * 
 */
package jp.co.sfrontier.ss3.game.service.game.core;

import java.time.LocalDateTime;

import jp.co.sfrontier.ss3.game.common.GameType;
import jp.co.sfrontier.ss3.game.common.ResultCode;
import jp.co.sfrontier.ss3.game.common.model.Player;
import jp.co.sfrontier.ss3.game.entity.history.ResultHistory;
import jp.co.sfrontier.ss3.game.repository.core.ResultHistoryRepository;
import lombok.RequiredArgsConstructor;

/**
 * 対戦履歴生成の共通処理を定義するクラス<br>
 * <br>
 */
@RequiredArgsConstructor
public abstract class AbstractHistoryRecorder<T extends GameAction, R extends GameResult>
		implements HistoryRecorder<T, R> {

	private final ResultHistoryRepository resultHistoryRepository;

	@Override
	public void record(Player<T> player1, Player<T> player2, R battleResult) {

		LocalDateTime now = LocalDateTime.now();

		// player1 視点の履歴を保存する
		saveHistory(player1, player2.getUserId(), battleResult.getResultCode(), now);

		// player2 視点の履歴を保存する
		saveHistory(player2, player1.getUserId(), battleResult.getResultCode().reverse(), now);
	}

	/**
	 * 対戦履歴を保存する
	 */
	private void saveHistory(Player<T> player, Long opponentUserId, ResultCode resultCode,
			LocalDateTime targetDate) {
		ResultHistory history = createHistory(player, opponentUserId, resultCode, targetDate);

		resultHistoryRepository.save(history);
	}

	/**
	 * 対戦履歴を作成する
	 */
	private ResultHistory createHistory(Player<T> player, Long opponentUserId, ResultCode resultCode,
			LocalDateTime targetDate) {

		return ResultHistory.builder()
				.userId(Long.valueOf(player.getUserId()))
				.opponentUserId(Long.valueOf(opponentUserId))
				.gameType(getGameType())
				.resultCode(resultCode)
				.executeDatetime(targetDate)
				.createDatetime(targetDate)
				.updateDatetime(targetDate)
				.userChoice(getUserChoice(player))
				.version(1)
				.build();
	}
	
	protected abstract GameType getGameType();
	
	protected abstract String getUserChoice(Player<T> player);
}
