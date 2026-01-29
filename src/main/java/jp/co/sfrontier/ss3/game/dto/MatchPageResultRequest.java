package jp.co.sfrontier.ss3.game.dto;

import java.util.List;

import lombok.Data;

/**
 * 対戦履歴のページング結果を保持するクラス。
 *
 * <p>
 * 1ページ分の履歴データに加え、
 * 現在のページ番号、表示件数、総件数などの
 * ページングに必要な情報をまとめて管理する。
 * </p>
 */
@Data
public class MatchPageResultRequest {
	
    private List<MatchResultRequest> rows;
    private int page;
    private int size;
    private long totalCount;
    
    /**
     * 総件数と1ページあたりの表示件数から、
     * 全ページ数を計算して返す。
     *
     * @return 全ページ数
     */
    public int getTotalPages() {
        return (int)((totalCount + size - 1) / size);
    }

}
