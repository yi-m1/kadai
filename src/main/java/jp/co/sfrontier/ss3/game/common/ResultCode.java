package jp.co.sfrontier.ss3.game.common;

/**
 * 判定結果を表す enum <br>
 * <br>
 */
public enum ResultCode{

	// TODO 勝敗の数値を変更する
	LOSE(0, "負け"),

	WIN(1, "勝ち"),

	DRAW(2, "引き分け"),

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

	// TODO コメント記入 （優先度：低）
	/**
	 * DB の数値から ResultCode を取得する <br>
	 * <br>
	 * @param code DB に保存されている結果コード
	 * @return ResultCod DB から受け取った数値
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

	/**
	 * 判定結果を反転する<br>
	 * <br>
	 * 
	 * @param resultCode プレイヤーの勝敗
	 * @return CPU の勝敗
	 */
	public ResultCode reverse() {

		return switch (this) {

		case WIN -> ResultCode.LOSE;

		case LOSE -> ResultCode.WIN;

		case DRAW -> ResultCode.DRAW;

		default -> this;
		};
	}

}
