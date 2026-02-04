package jp.co.sfrontier.ss3.game.service.janken;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sfrontier.ss3.game.common.Hand;
import jp.co.sfrontier.ss3.game.entity.ResultHistoryTbl;
import jp.co.sfrontier.ss3.game.repository.DbUtil;
import jp.co.sfrontier.ss3.game.repository.ResultHistoryTblDao;
import jp.co.sfrontier.ss3.game.value.Player;
import lombok.extern.slf4j.Slf4j;

/**
 * じゃんけんゲームを提供するサービスクラス（Spring化）
 */
@Slf4j
@Service
public class JankenService {

    /** CPUのユーザID */
    public static final int CPU_ID = 0;

    /** game_type_mst：0 = じゃんけん */
    private static final int GAME_TYPE_JANKEN = 0;

    /**
     * プレイヤー vs CPU のじゃんけん対戦
     */
    public int fight(Player player) throws SQLException {
        Player cpu = new Player(CPU_ID, createHand());
        return fight(player, cpu);
    }

    /**
     * プレイヤー同士のじゃんけん対戦を行い、結果をDBに保存
     */
    @Transactional
    public int fight(Player player1, Player player2) throws SQLException {

        // 勝敗判定
        int result = player1.getHand().compair(player2.getHand());

        Date now = new Date();
        Connection connection = DbUtil.getConnection();
        log.debug("connection get");

        ResultHistoryTblDao dao = new ResultHistoryTblDao(connection);
        log.debug("tblDao new");

        try {
            // player1 視点の結果
            dao.insert(createRecord(player1, player2.getUserId(), result, now));

            // player2 視点の結果（勝敗反転）
            dao.insert(createRecord(player2, player1.getUserId(), result * -1, now));

            DbUtil.commit(connection);
            log.debug("connection commit");

        } catch (SQLException e) {
            log.error("DB保存中にエラー", e);
            DbUtil.rollback(connection);
            throw e;
        } finally {
            DbUtil.close(connection);
            log.debug("connection close");
        }

        return result;
    }

    /**
     * 対戦履歴レコードを作成（DDL完全準拠）
     */
    private ResultHistoryTbl createRecord(
            Player player,
            int opponentId,
            int result,
            Date targetDate) {

        ResultHistoryTbl entity = new ResultHistoryTbl();

        entity.setUserId(player.getUserId());
        entity.setOpponent(opponentId);
        entity.setGameTypeId(GAME_TYPE_JANKEN); // じゃんけん
        entity.setResultId(convertResultId(result)); // 勝敗ID
        entity.setExecuteDatetime(targetDate);
        entity.setCreateDatetime(targetDate);
        entity.setUpdateDatetime(targetDate);
        entity.setUserChoice(player.getHand().name());
        entity.setVersion(1);

        return entity;
    }

    /**
     * CPUの手をランダム生成
     */
    private Hand createHand() {
        int randomNumber = new Random().nextInt(3);
        return switch (randomNumber) {
        case 0 -> Hand.ROCK;
        case 1 -> Hand.SCISSORS;
        case 2 -> Hand.PAPER;
        default -> throw new IllegalStateException("Unexpected value: " + randomNumber);
        };
    }

    /**
     * 勝敗結果を result_id に変換
     *
     * result_mst:
     * 0 = 勝ち
     * 1 = 負け
     * 2 = あいこ
     */
    private int convertResultId(int result) {
        if (result > 0) {
            return 0; // 勝ち
        } else if (result == 0) {
            return 2; // あいこ
        } else {
            return 1; // 負け
        }
    }
}