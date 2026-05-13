/**
 * 
 */
package jp.co.sfrontier.ss3.game.service.janken;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.game.common.ResultCode;
import jp.co.sfrontier.ss3.game.entity.ResultHistoryTbl;
import jp.co.sfrontier.ss3.game.model.GameType;
import jp.co.sfrontier.ss3.game.repository.DbUtil;
import jp.co.sfrontier.ss3.game.repository.ResultHistoryTblDao;
import jp.co.sfrontier.ss3.game.service.core.BattleResult;
import jp.co.sfrontier.ss3.game.service.core.HistoryRecorder;
import jp.co.sfrontier.ss3.game.value.Player;

/**
 * じゃんけん用の対戦履歴保存処理を提供するクラス<br>
 * <br>
 */
@Service
public class JankenHistoryRecorder implements HistoryRecorder {

	@Override
	public void record(Player player1, Player player2, BattleResult battleResult) throws SQLException {

		Date now = new Date();

		Connection connection = DbUtil.getConnection();

		ResultHistoryTblDao dao = new ResultHistoryTblDao(connection);

		// TODO （中）じゃんけんの保存処理をあっちむいてほいと共通にする
		try {

			dao.insert(createRecord(player1, player2.getUserId(), battleResult.getResultCode(), now));

			dao.insert(createRecord(player2, player1.getUserId(), battleResult.getResultCode().reverse(battleResult.getResultCode()), now));

			DbUtil.commit(connection);

		} catch (SQLException e) {

			DbUtil.rollback(connection);

			throw e;

		} finally {

			DbUtil.close(connection);
		}
	}

	/**
	 * 対戦履歴レコードを作成（DDL完全準拠）
	 */
	private ResultHistoryTbl createRecord(Player player,int opponentId,ResultCode resultCode,Date targetDate) {

		ResultHistoryTbl entity = new ResultHistoryTbl();

		entity.setUserId(player.getUserId());
		entity.setOpponent(opponentId);
		entity.setGameTypeId(GameType.JANKEN.getId()); // じゃんけん
		entity.setResultId(resultCode.getCode()); // 勝敗ID
		entity.setExecuteDatetime(targetDate);
		entity.setCreateDatetime(targetDate);
		entity.setUpdateDatetime(targetDate);
		entity.setUserChoice(player.getHand().name());
		entity.setVersion(1);

		return entity;
	}

}
