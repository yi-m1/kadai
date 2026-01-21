package jp.co.sfrontier.ss3.janken_game.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.sfrontier.ss3.janken_game.dto.ResultHistoryDto;

@Mapper
public interface ResultHistoryMapper {
	/**
	 * 対戦履歴一覧を取得する。
	 *
	 * <p>
	 * 引数 gameTypeId が null の場合は
	 * ゲームタイプによる絞り込みを行わず、
	 * 全ゲーム種別の履歴を対象として取得する。
	 * </p>
	 *
	 * @param gameTypeId ゲームタイプID（null の場合は全件取得）
	 * @return 条件に合致する対戦履歴の一覧
	 */
	List<ResultHistoryDto> selectHistoryRows(@Param("userId") Long userId, @Param("gameTypeId") Integer gameTypeId);
}