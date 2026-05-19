/**
 * 
 */
package jp.co.sfrontier.ss3.game.service.user;

import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.game.entity.user.UserInformation;
import jp.co.sfrontier.ss3.game.repository.UserRepository;
import lombok.RequiredArgsConstructor;

/**
 * JPA動作確認用サービス
 */
@Service
@RequiredArgsConstructor
public class UserQueryService {
	
	private final UserRepository userRepository;

	public UserInformation findUser(Long userId) {
		
		 System.out.println("リポジトリ到達前");
		 
		 UserInformation user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("ユーザが見つかりません"));

		 System.out.println("リポジトリ到達後");
		return user;
	}
}
