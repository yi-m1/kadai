package jp.co.sfrontier.ss3.game.common.model;

import java.io.Serializable;

import jp.co.sfrontier.ss3.game.service.game.core.GameAction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * プレイヤーの情報を保持するクラス<br>
 * <br>
 */
@Getter
@RequiredArgsConstructor
public class Player<T extends GameAction> implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ユーザーID */
	private final Long userId;

	/**  プレイヤーの行動 */
	private final T action;

}
