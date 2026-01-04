package jp.co.sfrontier.ss3.game.common;

/**
 * 処理結果を定義する<br>
 * 
 */
public enum ResultCode {

	WIN(1),

	LOSE(2),

	DRAW(3),

	INIT(4);

	private final int code;

	ResultCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}