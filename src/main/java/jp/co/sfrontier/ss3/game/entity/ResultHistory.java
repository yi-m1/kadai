package jp.co.sfrontier.ss3.game.entity;

import java.util.Date;

import lombok.Data;

/**
 * RESULT_HISTORY_TBL 用のEntity
 */
@Data
public class ResultHistory {
	  private Long historyId;
	  private Long userId;
	  private Integer gameTypeId;
	  private Integer resultId;
	  private Date executeDatetime;
	  private Long opponent;
	  private String userChoice;
	  private Date createDatetime;
	  private Date updateDatetime;
	  private Integer version;
}
