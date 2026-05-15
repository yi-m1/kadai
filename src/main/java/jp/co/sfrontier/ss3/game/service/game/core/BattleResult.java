/**
 * 
 */
package jp.co.sfrontier.ss3.game.service.game.core;

import jp.co.sfrontier.ss3.game.common.ResultCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 対戦結果の情報を提供するクラス<br>
 * <br>
 */
@Getter
@RequiredArgsConstructor
public class BattleResult implements GameResult {

	private final ResultCode resultCode;
}
