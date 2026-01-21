package jp.co.sfrontier.ss3.janken_game.dto;

/**
 * 画面に渡すためのデータの入れ物
 */
public class ResultHistoryDto {
	private String userName;
	private int gameTypeId; // 0 / 1（判定用）
	private String gameTypeName; // "じゃんけん"（表示用）
	private String executeDatetime;
	private String opponent;
	private String result;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getGameTypeId() {
		return gameTypeId;
	}

	public void setGameTypeId(int gameTypeId) {
		this.gameTypeId = gameTypeId;
	}

	public String getGameTypeName() {
		return gameTypeName;
	}

	public void setGameTypeName(String gameTypeName) {
		this.gameTypeName = gameTypeName;
	}

	public String getExecuteDatetime() {
		return executeDatetime;
	}

	public void setExecuteDatetime(String executeDatetime) {
		this.executeDatetime = executeDatetime;
	}

	public String getOpponent() {
		return opponent;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
