package jp.co.sfrontier.ss3.game.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import jp.co.sfrontier.ss3.game.entity.ResultHistory;
import lombok.extern.slf4j.Slf4j;

/**
 * ResultHistory insert専用 DAO クラス<br>
 * <br>
 */
@Slf4j
public class ResultHistoryInsertDao {

	private final Connection connection;

	public ResultHistoryInsertDao(Connection connection) {
		this.connection = connection;
	}

	/**
	 * 履歴テーブルにレコードをINSERT
	 * TODO コメント修正
	 * @param history ResultHistoryTbl Entity
	 * @throws SQLException
	 */
	public void insert(ResultHistory history) throws SQLException {
		String sql = "INSERT INTO result_history_tbl "
				+ "(user_id, game_type_id, result_id, execute_datetime, opponent, user_choice, create_datetime, update_datetime, version) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";

		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			int idx = 0;

			pstmt.setLong(++idx, history.getUserId()); // user_id (BIGINT)
			pstmt.setInt(++idx, history.getGameType().getId()); // game_type_id
			pstmt.setInt(++idx, history.getResultCode().getCode()); // result_id
			pstmt.setTimestamp(++idx, Timestamp.valueOf(history.getExecuteDatetime()));
			pstmt.setLong(++idx, history.getOpponentUserId()); // opponent (BIGINT)
			pstmt.setString(++idx, history.getUserChoice()); // user_choice
			pstmt.setTimestamp(++idx, Timestamp.valueOf(history.getCreateDatetime()));
			pstmt.setTimestamp(++idx, Timestamp.valueOf(history.getUpdateDatetime()));
			pstmt.setInt(++idx, history.getVersion()); // version

			int rowsInserted = pstmt.executeUpdate();
			if (rowsInserted == 0) {
				log.warn("No rows inserted into result_history_tbl for user_id={}", history.getUserId());
			} else {
				log.debug("Inserted {} row(s) into result_history_tbl for user_id={}", rowsInserted,
						history.getUserId());
			}
		} catch (SQLException e) {
			log.error("Failed to insert record into result_history_tbl for user_id={}", history.getUserId(), e);
			throw e;
		}
	}
}
