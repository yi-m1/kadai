package jp.co.sfrontier.ss3.game.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jp.co.sfrontier.ss3.game.entity.ResultHistoryTbl;

/**
 * ResultHistoryTbl DAO クラス（DDL対応版）
 */
public class ResultHistoryTblDao {

    private static final Logger logger = LogManager.getLogger(ResultHistoryTblDao.class);

    private final Connection connection;

    public ResultHistoryTblDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * 履歴テーブルにレコードをINSERT
     *
     * @param record ResultHistoryTbl Entity
     * @throws SQLException
     */
    public void insert(ResultHistoryTbl record) throws SQLException {
        String sql = "INSERT INTO result_history_tbl "
                + "(user_id, game_type_id, result_id, execute_datetime, opponent, user_choice, create_datetime, update_datetime, version) "
                + "VALUES (?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            int idx = 0;

            pstmt.setLong(++idx, record.getUserId()); // user_id (BIGINT)
            pstmt.setInt(++idx, record.getGameTypeId()); // game_type_id
            pstmt.setInt(++idx, record.getResultId()); // result_id
            pstmt.setTimestamp(++idx, new Timestamp(record.getExecuteDatetime().getTime())); // execute_datetime
            pstmt.setLong(++idx, record.getOpponent()); // opponent (BIGINT)
            pstmt.setString(++idx, record.getUserChoice()); // user_choice
            pstmt.setTimestamp(++idx, new Timestamp(record.getCreateDatetime().getTime())); // create_datetime
            pstmt.setTimestamp(++idx, new Timestamp(record.getUpdateDatetime().getTime())); // update_datetime
            pstmt.setInt(++idx, record.getVersion()); // version

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted == 0) {
                logger.warn("No rows inserted into result_history_tbl for user_id={}", record.getUserId());
            } else {
                logger.debug("Inserted {} row(s) into result_history_tbl for user_id={}", rowsInserted,
                        record.getUserId());
            }
        } catch (SQLException e) {
            logger.error("Failed to insert record into result_history_tbl for user_id={}", record.getUserId(), e);
            throw e;
        }
    }
}
