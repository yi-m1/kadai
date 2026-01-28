package jp.co.sfrontier.ss3.game.service.history;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sfrontier.ss3.game.model.ResultHistory;
import jp.co.sfrontier.ss3.game.repository.ResultHistoryDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 対戦履歴に関するサービスクラス
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HistoryService {

    private final ResultHistoryDao resultHistoryDao;

    /**
     * 該当ユーザーの対戦履歴を取得する
     *
     * @param userId ユーザーID
     * @return 対戦履歴リスト（降順）
     */
    @Transactional(readOnly = true)
    public List<ResultHistory> getHistory(int userId) {
        try {
            List<ResultHistory> list = resultHistoryDao.getResultHistoryInfo(userId);
            log.info("対戦履歴取得成功 userId={}, count={}", userId, list.size());
            return list;
        } catch (Exception e) {
            log.error("対戦履歴の取得に失敗 userId={}", userId, e);
            throw new RuntimeException("対戦履歴の取得に失敗しました", e);
        }
    }
}
