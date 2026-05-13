/**
 * 
 */
package jp.co.sfrontier.ss3.game.service.core;

/**
 * CPU の行動決定処理を定義するインタフェース<br>
 * <br>
 * 実装クラスごとに異なる行動決定方式を提供する。
 * 
 * @param <T> ゲーム内で使用する行動の型
 */
public interface CpuActionStrategy<T extends GameAction> {

	/**
	 * CPU の行動を取得する
	 * 
	 * @return CPU の行動
	 */
	public T getAction();

}