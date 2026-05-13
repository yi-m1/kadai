package jp.co.sfrontier.ss3.game.service.janken;

import java.util.Random;

import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.game.common.Hand;
import jp.co.sfrontier.ss3.game.service.core.CpuActionStrategy;

/**
 * じゃんけんにおけるCPUの行動を処理するクラス<br>
 * <br>
 */
@Service
public class CpuHandStrategy implements CpuActionStrategy<Hand> {

	/**
	 * CPU が選択した手を取得する
	 */
	@Override
	public Hand getAction() {

		int randomNumber = new Random().nextInt(3);
		
		return switch (randomNumber) {
		case 0 -> Hand.ROCK;
		case 1 -> Hand.SCISSORS;
		case 2 -> Hand.PAPER;
		default -> throw new IllegalStateException("Unexpected value: " + randomNumber);
		};
	}

}
