package jp.co.sfrontier.ss3.game.service.game.janken;

import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.game.service.game.core.AbstractRandomStrategy;
import jp.co.sfrontier.ss3.game.service.game.janken.value.Hand;

/**
 * じゃんけんにおけるCPUの行動を処理するクラス<br>
 * <br>
 */
@Service
public class CpuHandStrategy extends AbstractRandomStrategy<Hand> {

	/**
	 * CPU が選択した手を取得する
	 */
	@Override
	protected List<Hand> getActions() {
		
		return List.of(Hand.ROCK,Hand.SCISSORS,Hand.PAPER);
	}

}
