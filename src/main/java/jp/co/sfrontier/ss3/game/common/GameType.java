package jp.co.sfrontier.ss3.game.common;

/**
 * ゲームの種別を表す列挙型。
 */
public enum GameType {
	JANKEN(0, "じゃんけん"), LOOKOVERTHERE(1, "あっちむいてほい");

	/** DBに入る値 */
	private final int id;
	
	/** 画面に表示する名前 */
	private final String label;

	GameType(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}
	
    public static GameType fromId(int id) {
        for (GameType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown GameType id: " + id);
    }
}

