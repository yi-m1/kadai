package jp.co.sfrontier.ss3.game.controller.login;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sfrontier.ss3.game.model.ChangePasswordForm;
import jp.co.sfrontier.ss3.game.service.login.ChangePasswordService;

/**
 * パスワード変更を行うコントローラークラス
 */
@Controller
public class ChangePasswordController {

	@Autowired
	private ChangePasswordService changePasswordService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/changePassword")
	public String showChangePasswordPage(Model model) {
		model.addAttribute("changePasswordForm", new ChangePasswordForm());
		return "login/changePassword";
	}

	@PostMapping("/changePassword")
	public String changePassword(@Valid @ModelAttribute("changePasswordForm") ChangePasswordForm form,
			BindingResult bindingResult, Model model) {

		// 入力項目のバリデーションチェックを行う
		if (bindingResult.hasErrors()) {
			return "login/changePassword";
		}

		// パスワードを変更する
		boolean success = changePasswordService.changePassword(form.getUserNameOrMailAddress(), form.getNewPassword());

		// ユーザ名やメールアドレスが存在しない場合はエラーメッセージを出す
		if (!success) {
			model.addAttribute("errorMessage", "ユーザ名またはメールアドレスが存在しません");
			return "login/changePassword";
		}

		// パスワード変更したらログインしメニュー画面に遷移する
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				form.getUserNameOrMailAddress(), form.getNewPassword());

		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);

		return "redirect:/menu";
	}
}
