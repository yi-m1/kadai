/**
 * 
 */
package jp.co.sfrontier.ss3.game.service.core;

/**
 * 対戦相手の行動を定義するインタフェース<br>
 * <br>
 */
public interface OpponentActionStrategy<T> {

	T getAction();
}
