package jp.co.sfrontier.ss3.game.service.lookoverthere.value;

import jp.co.sfrontier.ss3.game.common.Direction;
import jp.co.sfrontier.ss3.game.common.ResultCode;
import lombok.Value;

/**
 * 1回のプレイ結果を Controller に返す Value <br>
 * <br>
 * private final フィールド、コンストラクタ、getter は自動生成される
 */
@Value
public class LookOverThereResult {

	ResultCode resultCode;
	Direction attackerDirection;
	Direction defenderDirection;

}
