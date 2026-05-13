/**
 * JankenGameDao.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jp.co.sfrontier.ss3.game.repository;

import java.sql.SQLException;

import jp.co.sfrontier.ss3.game.model.JankenResult;

/**
 *
 * @author FLM
 * @version 1.0.0
 */
public interface JankenGameDao {

	void recordGameResult(JankenResult gameResult) throws SQLException;

}
