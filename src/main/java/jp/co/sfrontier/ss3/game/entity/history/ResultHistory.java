package jp.co.sfrontier.ss3.game.entity.history;

import java.time.LocalDateTime;

import jp.co.sfrontier.ss3.game.common.GameType;
import jp.co.sfrontier.ss3.game.common.ResultCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * RESULT_HISTORY_TBL 用のEntity<br>
 * <br>
 */
@Getter
@Setter
@Builder
public class ResultHistory {

	private Long historyId;

	private Long userId;

	private Long opponentUserId;

	private GameType gameType;

	private ResultCode resultCode;

	private LocalDateTime executeDatetime;

	private String userChoice;

	private LocalDateTime createDatetime;

	private LocalDateTime updateDatetime;

	private Integer version;
}
