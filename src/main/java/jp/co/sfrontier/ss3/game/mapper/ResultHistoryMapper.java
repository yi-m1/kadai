package jp.co.sfrontier.ss3.game.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.co.sfrontier.ss3.game.entity.ResultHistory;

@Mapper
public interface ResultHistoryMapper {
	void insert(ResultHistory history);
}
