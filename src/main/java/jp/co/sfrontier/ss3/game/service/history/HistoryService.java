package jp.co.sfrontier.ss3.game.service.history;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.game.dto.MatchResultRequest;
import jp.co.sfrontier.ss3.game.mapper.MatchResultMapper;
import jp.co.sfrontier.ss3.game.model.GameType;

/**
 * 対戦履歴に関する処理を提供するサービスクラス。
 */

@Service
public class HistoryService {

	@Autowired
	private MatchResultMapper resultHistoryMapper;

	/**
	 * すべての対戦履歴一覧を取得する。
	 *
	 * <p>
	 * Mapper に null を渡すことで、
	 * MatchResultMapper.xmlで絞り込み条件をしない検索を行う。
	 * </p>
	 *
	 * @return 全対戦履歴の一覧。履歴がなければ空のList
	 */
	public List<MatchResultRequest> findAll() {
		return resultHistoryMapper.selectHistoryRows(null);
	}

	/**
	 * 指定されたゲームタイプに該当する対戦履歴一覧を取得する。
	 * 
	 * <p>
	 * 引数で受け取った GameType からゲームタイプIDを取得し、
	 * DB検索用の条件として Mapper に渡す。
	 * </p>
	 * 
	 * @param gameType 取得対象のゲームタイプ（例：JANKEN、ACCHI）
	 * @return 指定されたゲームタイプに該当する履歴の一覧。履歴がなければ空のList
	 */
	public List<MatchResultRequest> findByGameType(GameType gameType) {
		return resultHistoryMapper.selectHistoryRows(gameType.getId());
	}
}
