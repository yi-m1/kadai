package jp.co.sfrontier.ss3.game.common;

public enum ResultCode {

	WIN(1, "勝ち"),

	LOSE(0, "負け"),

	INIT(10, "初期化失敗"),
	;

	private final int code;
	private final String label;

	ResultCode(int code, String label) {
		this.code = code;
		this.label = label;
	}

	public int getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}
}
