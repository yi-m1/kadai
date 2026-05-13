package jp.co.sfrontier.ss3.game.service.lookoverthere;

import java.util.Random;

import jp.co.sfrontier.ss3.game.common.Direction;
import jp.co.sfrontier.ss3.game.service.core.CpuActionStrategy;

/**
 * あっちむいてほいにおける CPU の行動を処理するクラス<br>
 * <br>
 */
public class CpuDirectionStrategy implements CpuActionStrategy<Direction> {

	/**
	 * CPU が選択した方向を取得する
	 */
	@Override
	public Direction getAction() {

		Direction[] values = Direction.values();

		return values[new Random().nextInt(values.length)];
	}

}
