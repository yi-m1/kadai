/**
 * 
 */
package jp.co.sfrontier.ss3.game.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sfrontier.ss3.game.entity.user.UserInformation;
import jp.co.sfrontier.ss3.game.service.user.UserQueryService;
import lombok.RequiredArgsConstructor;

/**
 * JPA動作確認用コントローラ
 */
@RestController
@RequiredArgsConstructor
public class UserQueryController {
	
	private final UserQueryService userQueryService;
	
	@GetMapping("/test/users/{id}")
	public String getUser(@PathVariable Long id) {
		
		UserInformation user = userQueryService.findUser(id);
		
		System.out.println("コントローラ到達");
		
		return user.getUserName();
	}
}
