package jp.co.sfrontier.ss3.game.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sfrontier.ss3.game.common.ResultCode;
import jp.co.sfrontier.ss3.game.entity.MatchResult;
import jp.co.sfrontier.ss3.game.entity.ResultHistory;
import jp.co.sfrontier.ss3.game.mapper.MatchResultMapper;
import jp.co.sfrontier.ss3.game.mapper.ResultHistoryMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 対戦結果を保存するサービスクラス<br>
 * <br>
 */
@Slf4j
@Service
@Transactional
public class MatchResultService {

	private final MatchResultMapper mapper; // 既存
	private final ResultHistoryMapper resultHistoryMapper; // 追加

	public MatchResultService(MatchResultMapper mapper, ResultHistoryMapper resultHistoryMapper) {
		this.mapper = mapper;
		this.resultHistoryMapper = resultHistoryMapper;
	}

	public void save(
			Long attackerId,
			Long defenderId,
			ResultCode resultCode,
			Integer attackerDirection,
			Integer defenderDirection,
			Long gameId) {
		// ===== 既存：GAME.MATCH_RESULT =====
		MatchResult result = new MatchResult();
		result.setAttackerId(attackerId);
		result.setDefenderId(defenderId);
		result.setGameId(gameId);
		result.setJudge(resultCode.getCode());
		result.setAttackerDirection(attackerDirection);
		result.setDefenderDirection(defenderDirection);

		Date now = new Date();
		result.setMatchDatetime(now);
		result.setCreatedAt(now);
		result.setUpdatedAt(now);

		result.setVersion(1);
		try {
			mapper.insert(result);
			log.info("対戦結果を保存しました matchId={}", result.getMatchResultId());
		} catch (Exception e) {
			log.error("対戦結果の保存に失敗しました result={}", result, e);
			throw e; // 上位に伝播させる
		}

		// ===== 追加：RESULT_HISTORY_TBL =====
		ResultHistory history = new ResultHistory();
		history.setUserId(attackerId);
		history.setOpponent(defenderId);

		// game_type_id は 0/1
		// LookOverTherePlayService の GAME_ID=2L は、そのままだと FKで落ちる可能性大。
		history.setGameTypeId(convertGameTypeId(gameId)); // 2 -> 1 にする

		history.setResultId(resultCode.getCode()); // WIN=1, LOSE=0
		history.setExecuteDatetime(now);
		history.setCreateDatetime(now);
		history.setUpdateDatetime(now);
		history.setVersion(1);
		history.setUserChoice(null);

		resultHistoryMapper.insert(history);

		log.info("保存完了 matchResultId={}, historyId={}",
				result.getMatchResultId(), history.getHistoryId());
	}

	/**
	 * GAME.MATCH_RESULT.GAME_ID(2)をRESULT_HISTORY_TBL.game_type_id(1)に合わせる。
	 * @param gameId GAME.MATCH_RESULT.GAME_ID（あっちむいてほい=2）
	 * @return gameId RESULT_HISTORY_TBL.game_type_id(あっちむいてほい=1)
	 */
	private Integer convertGameTypeId(Long gameId) {
		if (gameId == null)
			return null;
		// あっちむいてほい: GAME_ID=2 → game_type_id=1
		if (gameId.longValue() == 2L)
			return 1;
		return gameId.intValue();
	}
}
