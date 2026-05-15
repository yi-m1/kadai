/**
 * 
 */
package jp.co.sfrontier.ss3.game.service.game.core;

import java.util.List;
import java.util.Random;

/**
 * ランダムな CPU の行動を定義する抽象クラス
 */
public abstract class AbstractRandomStrategy<T extends GameAction> implements CpuActionStrategy<T> {

	private final Random random = new Random();

	@Override
	public T getAction() {

		List<T> actions = getActions();

		return actions.get(random.nextInt(actions.size()));
	}

	/**
	 * ゲームごとに異なる行動候補を取得する
	 * 
	 * <p>
	 * 例）じゃんけん：グー、チョキ、パー
	 * </p>
	 */
	protected abstract List<T> getActions();

}
