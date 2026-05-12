/**
 * JankenGameDaoImpl.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jp.co.sfrontier.ss3.game.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.sfrontier.ss3.game.model.GameResult;
import jp.co.sfrontier.ss3.game.model.GameType;

/**
 * じゃんけん結果を result_history_tbl に登録するDAO<br>
 * <br>
 */
public class JankenGameDaoImpl implements JankenGameDao {

	private static final int GAME_TYPE_JANKEN = GameType.JANKEN.getId();

	private Connection connection;

	public JankenGameDaoImpl(Connection connection) {
		this.connection = connection;
	}

	private Connection getConnection() {
		if (connection == null) {
			throw new IllegalStateException("Connection is not initialized");
		}
		return connection;
	}

	/**
	 * ゲームの結果をデータベースに記録する
	 *
	 * @param gameResult ゲーム結果
	 * @throws SQLException SQL例外
	 */
	@Override
	public void recordGameResult(GameResult gameResult) throws SQLException {

		String sql = "INSERT INTO result_history_tbl ("
				+ " user_id, game_type_id, result_id, execute_datetime, opponent,"
				+ " user_choice, create_datetime, update_datetime, version"
				+ ") VALUES ("
				+ " ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1"
				+ ")";

		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

			ps.setLong(1, gameResult.getUserId()); // user_id
			ps.setInt(2, GAME_TYPE_JANKEN); // game_type_id（じゃんけん）
			ps.setInt(3, convertResultId(gameResult)); // result_id
			ps.setLong(4, gameResult.getUserId()); // opponent
			ps.setString(5, gameResult.getUserChoice()); // user_choice

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLException(
					"Error while recording janken result. userId="
							+ gameResult.getUserId(),
					e);
		}
	}

	/**
	 * 勝敗文字列を result_id に変換
	 *
	 * result_mst:
	 * 0 = 勝ち
	 * 1 = 負け
	 * 2 = あいこ
	 */
	private int convertResultId(GameResult gameResult) {
		return switch (gameResult.getResultMessage()) {
		case "WIN" -> 0;
		case "LOSE" -> 1;
		case "DROW" -> 2;
		default -> throw new IllegalArgumentException(
				"Unknown result message: " + gameResult.getResultMessage());
		};
	}
}
