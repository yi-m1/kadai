package jp.co.sfrontier.ss3.game.model;

/**
 * TODO じゃんけんで使用するモデルクラスのリファクタリング（優先度：低）
 */
public class JankenResult {
    private int userId;
    private String userChoice;
    private String aiChoice;
    private String resultMessage;

    // コンストラクタ
    public JankenResult(int userId, String userChoice, String aiChoice, String resultMessage) {
        this.userId = userId;
        this.userChoice = userChoice;
        this.aiChoice = aiChoice;
        this.resultMessage = resultMessage;
    }

    // ゲッターとセッター
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(String userChoice) {
        this.userChoice = userChoice;
    }

    public String getAiChoice() {
        return aiChoice;
    }

    public void setAiChoice(String aiChoice) {
        this.aiChoice = aiChoice;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
