package jp.co.sfrontier.ss3.game.service.history;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.game.dto.MatchResultRequest;
import jp.co.sfrontier.ss3.game.mapper.MatchResultMapper;
import jp.co.sfrontier.ss3.game.model.GameType;
import jp.co.sfrontier.ss3.game.service.login.LoginUserDetails;

/**
 * 対戦履歴に関する処理を提供するサービスクラス。
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
  * すべての対戦履歴一覧を取得する。
  *
  * <p>
  * Mapper に null を渡すことで、 MatchResultMapper.xmlで絞り込み条件をしない検索を行う。
  * </p>
  *
  * @return 全対戦履歴の一覧。履歴がなければ空のList
  */
 public List<MatchResultRequest> findAll() {
  return matchResultMapper.selectHistoryRows(getFilterUserId(), null);
 }

 /**
  * 指定されたゲームタイプに該当する対戦履歴一覧を取得する。
  *
  * <p>
  * 引数で受け取った GameType からゲームタイプIDを取得し、 DB検索用の条件として Mapper に渡す。
  * </p>
  *
  * @param gameType 取得対象のゲームタイプ（例：JANKEN、ACCHI）
  * @return 指定されたゲームタイプに該当する履歴の一覧。履歴がなければ空のList
  */
 public List<MatchResultRequest> findByGameType(GameType gameType) {
  return matchResultMapper.selectHistoryRows(getFilterUserId(), gameType.getId());
 }
}