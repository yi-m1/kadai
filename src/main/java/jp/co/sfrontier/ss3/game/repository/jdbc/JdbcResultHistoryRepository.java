package jp.co.sfrontier.ss3.game.repository.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import jp.co.sfrontier.ss3.game.entity.history.ResultHistory;
import jp.co.sfrontier.ss3.game.repository.core.ResultHistoryRepository;
import jp.co.sfrontier.ss3.game.repository.jdbc.dao.ResultHistoryInsertDao;
import jp.co.sfrontier.ss3.game.repository.jdbc.util.DbUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * JDBC を使用した対戦履歴リポジトリ
 */
@Slf4j
@Repository
public class JdbcResultHistoryRepository implements ResultHistoryRepository {

	@Override
	public void save(ResultHistory history) {

		Connection connection = null;

		try {

			connection = DbUtil.getConnection();

			ResultHistoryInsertDao dao = new ResultHistoryInsertDao(connection);

			dao.insert(history);

			DbUtil.commit(connection);

		} catch (SQLException e) {

			log.error("対戦履歴保存中にエラー", e);

			try {
				
				DbUtil.rollback(connection);
				
			} catch (SQLException rollbackException) {
				
				log.error("ロールバック失敗", rollbackException);
				
			}

			throw new RuntimeException("対戦履歴の保存に失敗しました", e);

		} finally {

			DbUtil.close(connection);
		}
	}

}
