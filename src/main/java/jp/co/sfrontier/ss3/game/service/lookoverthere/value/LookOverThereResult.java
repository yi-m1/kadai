package jp.co.sfrontier.ss3.game.service.lookoverthere.value;

import jp.co.sfrontier.ss3.game.common.Direction;
import jp.co.sfrontier.ss3.game.common.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 1回のプレイ結果を Controller に返す Value <br>
 * <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LookOverThereResult {

	ResultCode resultCode;
	Direction attackerDirection;
	Direction defenderDirection;
	String faceImage;

}
