package jp.co.sfrontier.ss3.game.service.lookoverthere;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sfrontier.ss3.game.common.Direction;
import jp.co.sfrontier.ss3.game.common.ResultCode;
import jp.co.sfrontier.ss3.game.mapper.MatchResultMapper;
import jp.co.sfrontier.ss3.game.service.MatchResultService;
import jp.co.sfrontier.ss3.game.service.lookoverthere.value.LookOverThereResult;
import jp.co.sfrontier.ss3.game.value.LookOverThereMatchHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 「あっちむいてほい」についての各種サービスを提供するクラス<br>
 * <ul>
 * <li>「あっちむいてほい」の勝敗を判定する
 * <li>判定結果を履歴に保存する
 * <li>Controller に返却する結果を生成する
 * </ul>
 * <br>
 * ※現在は CPU 対戦のみだが、Defender の方向決定ロジックを切り出しているため、
 * 将来的に対人戦へ拡張可能である
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LookOverTherePlayService {

	//	private static final Logger logger = LoggerFactory.getLogger(AcchiMuiteHoiService.class);

	/** 「あっちむいてほい」のゲームID */
	public static final Long GAME_ID = Long.valueOf(2L);

	/** CPU のプレイヤーID */
	private static final Long CPU_ID = Long.valueOf(0L);

	private final MatchResultService matchResultService;

	private final MatchResultMapper matchResultMapper;

	private final Random random = new Random();

	/** 
	 * 「あっちむいてほい」を1回実行し、結果を保存した上で Controller 用の結果を返す<br>
	 * <br>
	 * 
	 * @param attackerDirection アタッカーが選択した方向
	 * @return 勝敗結果と ディフェンダーの方向
	 */
	public LookOverThereResult play(Direction attackerDirection) {

		log.debug("play() 開始 attackerDirection={}", attackerDirection);

		Direction defenderDirection = decideDefenderDirection();

		log.debug("CPU方向決定 defenderDirection={}", defenderDirection);

		ResultCode resultCode = judge(attackerDirection, defenderDirection);

		log.info("勝敗判定 attacker={}, defender={}, result={}",
				attackerDirection, defenderDirection, resultCode);

		saveMatchResult(attackerDirection, defenderDirection, resultCode);

		// Controller 用の結果を返す
		return new LookOverThereResult(resultCode, attackerDirection, defenderDirection);
	}

	/**
	 * 「あっちむいてほい」の勝敗を判定する<br>
	 * <br>
	 * ルール：<br>
	 * アタッカーとディフェンダーの方向が同じならアタッカーの勝利、<br>
	 * 異なる方向ならアタッカーの敗北。<br>
	 * 
	 * @param attacker アタッカーの方向
	 * @param defender ディフェンダーの方向
	 * @return 勝敗結果
	 */
	private ResultCode judge(Direction attacker, Direction defender) {
		return attacker == defender ? ResultCode.WIN : ResultCode.LOSE;
	}

	/**
	 * 対戦結果を履歴に保存する<br>
	 * <br>
	 * 
	 * @param attackerDirection アタッカーの方向
	 * @param defenderDirection ディフェンダーの方向
	 * @param resultCode 勝敗結果
	 */
	private void saveMatchResult(
			Direction attackerDirection,
			Direction defenderDirection,
			ResultCode resultCode) {
		// TODO 後でセッション連携する
		Long attackerId = 1L;
		Long defenderId = CPU_ID;

		matchResultService.save(
				attackerId,
				defenderId,
				resultCode,
				attackerDirection.getVal(),
				defenderDirection.getVal(),
				GAME_ID);
	}

	/**
	 * ディフェンダーの方向を決定する<br>
	 * <br>
	 * 現在は CPU のためランダムに方向を決定する<br>
	 * 対人戦に拡張する場合は Controller で受け取ったディフェンダーの入力値を渡す形にする
	 * @return
	 */
	private Direction decideDefenderDirection() {
		return getRandomDirection();
	}

	/**
	 * 方向をランダムに取得する<br>
	 * <br>
	 * @return 上・下・左・右のランダムな方向
	 */
	private Direction getRandomDirection() {
		return Direction.get(random.nextInt(4) + 1);
	}

	/**
	 * 対戦履歴の一覧を取得する<br>
	 * <br>
	 * @param playerId プレイヤーID
	 * @return 対戦履歴の一覧
	 */
	public List<LookOverThereMatchHistory> getHistory(Long playerId) {
		return matchResultMapper.selectHistory(playerId);
	}

}
