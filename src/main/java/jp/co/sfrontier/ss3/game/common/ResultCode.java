package jp.co.sfrontier.ss3.game.common;

public enum ResultCode {
	
	LOSE(0, "負け"),

	WIN(1, "勝ち"),
	
	DRAW(2,"引き分け"),

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

	/**
     * DB の数値から ResultCode を取得する <br>
	 * <br>
	 * @param code 
	 * @return ResultCode  DB から受け取った数値
	 */ 
	public static ResultCode fromCode(Integer code) {
		if (code == null) {
			return null;
		}
		for (ResultCode rc : values()) {
			if (rc.code == code) {
				return rc;
			}
		}
		throw new IllegalArgumentException("Unknown ResultCode: " + code);
	}
}
