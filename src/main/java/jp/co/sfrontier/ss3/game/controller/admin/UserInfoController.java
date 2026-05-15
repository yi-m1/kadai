/**
 * UserInfoController.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jp.co.sfrontier.ss3.game.controller.admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sfrontier.ss3.game.dto.user.UserInfoResponse;
import jp.co.sfrontier.ss3.game.dto.user.UserSearchRequest;
import jp.co.sfrontier.ss3.game.dto.user.UserUpdateRequest;
import jp.co.sfrontier.ss3.game.service.admin.UserInfoService;

/**
 *
 * @author sf0537
 * @version 1.0.0
 */
@Controller
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	/**
	 * 一覧表示(初期表示)
	 */
	@GetMapping("/user/list")
	public String list(Model model) {

		List<UserInfoResponse> userList = userInfoService.findAll();

		model.addAttribute("userlist", userList);
		model.addAttribute("userSearchRequest", new UserSearchRequest());

		return "user/search";
	}

	/**
	 * 検索
	 */
	@PostMapping("/user/search")
	public String search(
			@ModelAttribute UserSearchRequest userSearchRequest,
			Model model) {

		List<UserInfoResponse> userList = userInfoService.search(userSearchRequest);

		model.addAttribute("userlist", userList);
		model.addAttribute("userSearchRequest",userSearchRequest);

		return "user/search";
	}

	/**
	 * 編集画面表示(status変更)
	 */
	@GetMapping("/user/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {

		UserInfoResponse user = userInfoService.findById(id);

		UserUpdateRequest request = new UserUpdateRequest();
		request.setUserId(user.getUserId());
		request.setStatus(user.getStatus());
		request.setVersion(user.getVersion());

		model.addAttribute("user", user);
		model.addAttribute("userUpdateRequest", request);

		return "user/edit";
	}

	/**
	 * status更新のみ
	 */
	@PostMapping("/user/status/update")
	public String updateStatus(
			@ModelAttribute UserUpdateRequest userUpdateRequest) {

		userInfoService.updateStatus(userUpdateRequest);

		return "redirect:/user/list";
	}
}
