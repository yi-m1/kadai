package jp.co.sfrontier.ss3.game.value;

import java.util.Date;

import lombok.Data;

/**
 * 「あっちむいてほい」の対戦履歴を画面表示用にまとめた DTO<br>
 * <br>
 */
@Data
public class LookOverThereMatchHistory {

	private Date matchDatetime;
	private String attackerDisplayName;
	private String defenderDisplayName;
	private Integer judge;
	private String winner;
	private Integer attackerDirection;
	private Integer defenderDirection;
	
}
