package jp.co.sfrontier.ss3.game.service.history;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.game.dto.MatchPageResultRequest;
import jp.co.sfrontier.ss3.game.dto.MatchResultRequest;
import jp.co.sfrontier.ss3.game.mapper.MatchResultMapper;
import jp.co.sfrontier.ss3.game.model.GameType;
import jp.co.sfrontier.ss3.game.service.login.LoginUserDetails;

/**
 * 対戦履歴に関する業務処理を提供する Service クラス。
 *
 * <p>
 * ログインユーザーの権限（管理者 / 一般ユーザー）を考慮し、
 * 取得対象のユーザーIDを決定した上で、
 * 対戦履歴の検索およびページング処理を行う。
 * </p>
 *
 * <p>
 * ページング処理では、総件数取得（COUNT）と
 * 指定ページ分の履歴取得を組み合わせて結果を返す。
 * </p>
 */

@Service
public class HistoryService {

    @Autowired
    private MatchResultMapper matchResultMapper;

    /**
     * 現在ログインしているユーザの権限に応じて、履歴検索用のユーザID（フィルタ条件）を取得する。
     * 
     * 管理ユーザ（ROLE_ADMIN）の場合は {@code null} を返し、 Mapper の SQL 側で USER_ID
     * による絞り込みを行わない。
     * 一般ユーザ（ROLE_USER）の場合は、ログインユーザ自身の userId を返し、自分の対戦履歴のみ取得する。
     *
     * @return 管理ユーザの場合は {@code null}、一般ユーザの場合はログインユーザの userId
     */
    private Long getFilterUserId() {

        // Spring Security のセキュリティコンテキストから、現在ログイン中の認証情報を取得する
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // 認証時に設定した UserDetails を取得する
        LoginUserDetails user = (LoginUserDetails) auth.getPrincipal();

        // ログインユーザが管理者権限(ROLE_ADMIN)を持っているか判定する
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        // 管理ユーザの場合 null を返し、一般ユーザの場合ログインユーザの userId を返す
        return isAdmin ? null : user.getUser().getUserId();
    }

    /**
     * 対戦履歴をページングして取得する（全ゲーム種別）。
     *
     * <p>
     * 指定されたページ番号と表示件数をもとに、
     * ログインユーザーが参照可能な履歴を
     * 新しい順で取得する。
     * </p>
     *
     * @param page 表示するページ番号（1始まり）
     * @param size 1ページあたりの表示件数
     * @return ページング情報および履歴一覧を保持した結果オブジェクト
     */
    public MatchPageResultRequest findPageAll(int page, int size) {
        Long userId = getFilterUserId();
        long total = matchResultMapper.countHistoryRows(userId, null);

        int safePage = Math.max(1, page);
        int offset = (safePage - 1) * size;

        List<MatchResultRequest> rows = matchResultMapper.selectHistoryRowsPage(userId, null, offset, size);

        MatchPageResultRequest res = new MatchPageResultRequest();
        res.setRows(rows);
        res.setPage(safePage);
        res.setSize(size);
        res.setTotalCount(total);
        return res;
    }

    /**
     * 指定したゲーム種別の対戦履歴をページングして取得する。
     *
     * <p>
     * ゲーム種別（じゃんけん / あっちむいてほい）で履歴を絞り込み、
     * 指定ページ分のデータを取得する。
     * </p>
     *
     * @param gameType 対象とするゲーム種別
     * @param page 表示するページ番号（1始まり）
     * @param size 1ページあたりの表示件数
     * @return ページング情報および履歴一覧を保持した結果オブジェクト
     */
    public MatchPageResultRequest findPageByGameType(GameType gameType, int page, int size) {
        Long userId = getFilterUserId();
        Integer gameTypeId = gameType.getId();

        long total = matchResultMapper.countHistoryRows(userId, gameTypeId);

        int safePage = Math.max(1, page);
        int offset = (safePage - 1) * size;

        List<MatchResultRequest> rows = matchResultMapper.selectHistoryRowsPage(userId, gameTypeId, offset, size);

        MatchPageResultRequest res = new MatchPageResultRequest();
        res.setRows(rows);
        res.setPage(safePage);
        res.setSize(size);
        res.setTotalCount(total);
        return res;
    }
}
