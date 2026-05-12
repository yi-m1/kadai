package jp.co.sfrontier.ss3.game.service.janken;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sfrontier.ss3.game.common.Hand;
import jp.co.sfrontier.ss3.game.common.ResultCode;
import jp.co.sfrontier.ss3.game.entity.ResultHistoryTbl;
import jp.co.sfrontier.ss3.game.model.GameType;
import jp.co.sfrontier.ss3.game.repository.DbUtil;
import jp.co.sfrontier.ss3.game.repository.ResultHistoryTblDao;
import jp.co.sfrontier.ss3.game.service.core.GameRule;
import jp.co.sfrontier.ss3.game.service.core.GameService;
import jp.co.sfrontier.ss3.game.service.core.OpponentActionStrategy;
import jp.co.sfrontier.ss3.game.value.Player;
import lombok.extern.slf4j.Slf4j;

/**
 * じゃんけんゲームを提供するサービスクラス
 */
@Slf4j
@Service
public class JankenService extends GameService<Hand> {

	public static final int CPU_ID = 0;

	private static final int GAME_TYPE_JANKEN = GameType.JANKEN.getId();

	public JankenService(OpponentActionStrategy<Hand> opponentActionStrategy, GameRule<Hand> gameRule) {

		super(opponentActionStrategy, gameRule);

	}

	/**
	 * プレイヤー vs CPU でじゃんけん対戦する
	 */
	public JankenResult fight(Player player) throws SQLException {

		// CPUの手を作成
		Player cpu = new Player(CPU_ID, getOpponentAction());

		// 勝敗判定
		ResultCode resultCode = fight(player, cpu);

		// 結果とCPUの手を返す
		return new JankenResult(resultCode, cpu.getHand());
	}

	/**
	 * プレイヤー同士でじゃんけん対戦する
	 */
	@Transactional
	public ResultCode fight(Player player1, Player player2) throws SQLException {

		// 勝敗判定
		ResultCode resultCode = judge(player1.getHand(), player2.getHand());

		saveHistory(player1, player2, resultCode);

		return resultCode;
	}

	/**
	 * 対戦履歴を保存する
	 */
	public void saveHistory(Player player1, Player player2, ResultCode resultCode) throws SQLException {
		Date now = new Date();
		Connection connection = DbUtil.getConnection();
		log.debug("connection get");

		ResultHistoryTblDao dao = new ResultHistoryTblDao(connection);
		log.debug("tblDao new");

		try {
			// player1 視点の結果
			dao.insert(createRecord(player1, player2.getUserId(), resultCode, now));

			// player2 視点の結果（勝敗反転）
			dao.insert(createRecord(player2, player1.getUserId(), reverse(resultCode), now));

			DbUtil.commit(connection);
			log.debug("connection commit");

		} catch (SQLException e) {
			log.error("DB保存中にエラー", e);
			DbUtil.rollback(connection);
			throw e;
		} finally {
			DbUtil.close(connection);
			log.debug("connection close");
		}
	}

	/**
	 * 対戦履歴レコードを作成（DDL完全準拠）
	 */
	private ResultHistoryTbl createRecord(
			Player player,
			int opponentId,
			ResultCode resultCode,
			Date targetDate) {

		ResultHistoryTbl entity = new ResultHistoryTbl();

		entity.setUserId(player.getUserId());
		entity.setOpponent(opponentId);
		entity.setGameTypeId(GAME_TYPE_JANKEN); // じゃんけん
		entity.setResultId(resultCode.getCode()); // 勝敗ID
		entity.setExecuteDatetime(targetDate);
		entity.setCreateDatetime(targetDate);
		entity.setUpdateDatetime(targetDate);
		entity.setUserChoice(player.getHand().name());
		entity.setVersion(1);

		return entity;
	}

	/**
	 * 勝敗を反転する
	 */
	private ResultCode reverse(ResultCode resultCode) {

		return switch (resultCode) {

		case WIN -> ResultCode.LOSE;

		case LOSE -> ResultCode.WIN;

		case DRAW -> ResultCode.DRAW;

		default -> resultCode;
		};
	}

}
