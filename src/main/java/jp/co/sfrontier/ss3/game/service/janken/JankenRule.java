/**
 * 
 */
package jp.co.sfrontier.ss3.game.service.janken;

import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.game.common.Hand;
import jp.co.sfrontier.ss3.game.common.ResultCode;
import jp.co.sfrontier.ss3.game.service.core.GameRule;

/**
 * じゃんけんゲーム固有の処理を提供するクラス<br>
 * <br>
 */
@Service
public class JankenRule implements GameRule<Hand> {

	@Override
	public ResultCode judge(Hand player1Action, Hand player2Action) {

		// 引き分け
		if (player1Action == player2Action) {
			return ResultCode.DRAW;
		}

		// player1 勝ち
		if (player1Action == Hand.ROCK
				&& player2Action == Hand.SCISSORS) {
			return ResultCode.WIN;
		}

		if (player1Action == Hand.SCISSORS
				&& player2Action == Hand.PAPER) {
			return ResultCode.WIN;
		}

		if (player1Action == Hand.PAPER
				&& player2Action == Hand.ROCK) {
			return ResultCode.WIN;
		}

		// それ以外は負け
		return ResultCode.LOSE;
	}

}
