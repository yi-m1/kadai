package jp.co.sfrontier.ss3.janken_game.service.history;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.janken_game.dto.ResultHistoryDto;
import jp.co.sfrontier.ss3.janken_game.mapper.ResultHistoryMapper;
import jp.co.sfrontier.ss3.janken_game.model.GameType;

/**
 * 対戦履歴に関する処理を提供するサービスクラス。
 */

@Service
public class HistoryService {

	@Autowired
	private ResultHistoryMapper resultHistoryMapper;

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
	public List<ResultHistoryDto> findAll(Long userId) {
		return resultHistoryMapper.selectHistoryRows(userId, null);
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
	public List<ResultHistoryDto> findByGameType(Long userId, GameType gameType) {
		return resultHistoryMapper.selectHistoryRows(userId, gameType.getId());
	}
}
