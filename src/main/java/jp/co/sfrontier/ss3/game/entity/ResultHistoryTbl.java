package jp.co.sfrontier.ss3.game.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 結果履歴テーブル result_history_tbl のエンティティクラス
 */
public class ResultHistoryTbl implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 履歴ID */
    private Long historyId;

    /** ユーザID */
    private int userId;

    /** ゲーム種別ID（0:じゃんけん / 1:あっちむいてほい） */
    private int gameTypeId;

    /** 結果ID（0:勝ち / 1:負け / 2:あいこ） */
    private int resultId;

    /** 実行日時 */
    private Date executeDatetime;

    /** 対戦相手ユーザID */
    private int opponent;

    /** ユーザの選択（ROCK / SCISSORS / PAPER 等） */
    private String userChoice;

    /** 作成日時 */
    private Date createDatetime;

    /** 更新日時 */
    private Date updateDatetime;

    /** バージョン */
    private Integer version;

    // ===== getter / setter =====

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameTypeId() {
        return gameTypeId;
    }

    public void setGameTypeId(int gameTypeId) {
        this.gameTypeId = gameTypeId;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public Date getExecuteDatetime() {
        return executeDatetime;
    }

    public void setExecuteDatetime(Date executeDatetime) {
        this.executeDatetime = executeDatetime;
    }

    public int getOpponent() {
        return opponent;
    }

    public void setOpponent(int opponent) {
        this.opponent = opponent;
    }

    public String getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(String userChoice) {
        this.userChoice = userChoice;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
