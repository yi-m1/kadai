/**
 * 
 */
package jp.co.sfrontier.ss3.game.service.game.janken;

import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.game.common.ResultCode;
import jp.co.sfrontier.ss3.game.service.game.core.AbstractGameRule;
import jp.co.sfrontier.ss3.game.service.game.janken.value.Hand;

/**
 * じゃんけんゲーム固有の処理を提供するクラス<br>
 * <br>
 */
@Service
public class JankenRule extends AbstractGameRule<Hand> {

	@Override
	protected ResultCode doJudge(Hand player1, Hand player2) {

		// 引き分け
		if (player1 == player2) {
			return ResultCode.DRAW;
		}

		// player1 勝ち
		if (player1 == Hand.ROCK
				&& player2 == Hand.SCISSORS) {
			return ResultCode.WIN;
		}

		if (player1 == Hand.SCISSORS
				&& player2 == Hand.PAPER) {
			return ResultCode.WIN;
		}

		if (player1 == Hand.PAPER
				&& player2 == Hand.ROCK) {
			return ResultCode.WIN;
		}

		return ResultCode.LOSE;
	}

}
