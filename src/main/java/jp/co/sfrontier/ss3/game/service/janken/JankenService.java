package jp.co.sfrontier.ss3.game.service.janken;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.game.common.Hand;
import jp.co.sfrontier.ss3.game.service.core.BattleResult;
import jp.co.sfrontier.ss3.game.service.core.CpuActionStrategy;
import jp.co.sfrontier.ss3.game.service.core.GameRule;
import jp.co.sfrontier.ss3.game.service.core.GameService;
import jp.co.sfrontier.ss3.game.service.core.HistoryRecorder;
import jp.co.sfrontier.ss3.game.value.Player;

/**
 * じゃんけんゲームの進行を行うクラス<br>
 * <br>
 */
@Service
public class JankenService extends GameService<Hand,BattleResult> {

	private final HistoryRecorder<Hand,BattleResult> historyRecorder;

	public static final int CPU_USER_ID = 0;

	public JankenService(CpuActionStrategy<Hand> opponentActionStrategy, GameRule<Hand,BattleResult> gameRule,
			HistoryRecorder<Hand,BattleResult> historyRecorder) {

		super(opponentActionStrategy, gameRule);

		this.historyRecorder = historyRecorder;

	}

	/**
	 * プレイヤー vs CPU でじゃんけん対戦する
	 */
	// TODO（中）Service から SQL 例外を出さないようにする。GameException を作成する。
	public JankenResult fight(Player<Hand> player) throws SQLException {

		// CPUの手を作成
		Player<Hand> cpu = new Player<Hand>(CPU_USER_ID, getOpponentAction());

		// 勝敗判定
		BattleResult battleResult = fight(player, cpu);

		// 結果とCPUの手を返す
		return new JankenResult(battleResult, cpu.getAction());
	}

	/**
	 * 対戦する
	 */
	public BattleResult fight(Player<Hand> player1, Player<Hand> player2) throws SQLException {

		// 勝敗判定
		BattleResult battleResult = judge(player1.getAction(), player2.getAction());

		// 対戦履歴を保存する
		historyRecorder.record(player1, player2, battleResult);

		return battleResult;
	}

}
