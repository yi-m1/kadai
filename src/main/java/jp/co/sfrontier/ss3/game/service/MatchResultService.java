/**
 * 
 */
package jp.co.sfrontier.ss3.game.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sfrontier.ss3.game.common.ResultCode;
import jp.co.sfrontier.ss3.game.mapper.MatchResultMapper;
import jp.co.sfrontier.ss3.game.model.MatchResult;

/**
 * 対戦結果を保存するサービスクラス<br>
 * <br>
 */
@Service
@Transactional
public class MatchResultService {

    private final MatchResultMapper mapper;

    public MatchResultService(MatchResultMapper mapper) {
        this.mapper = mapper;
    }

    public void save(
            Long attackerId,
            Long defenderId,
            ResultCode resultCode,
            Integer attackerDirection,
            Integer defenderDirection,
            Long gameId
    ) {
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

        mapper.insert(result);
    }
}
