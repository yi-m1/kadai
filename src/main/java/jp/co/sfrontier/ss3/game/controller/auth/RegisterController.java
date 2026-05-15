package jp.co.sfrontier.ss3.game.controller.auth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sfrontier.ss3.game.dto.auth.RegisterForm;
import jp.co.sfrontier.ss3.game.service.auth.RegisterService;

/**
 * 新規ユーザ登録処理を行うコントローラークラス
 */
@Controller
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("registerForm", new RegisterForm());
		return "login/register";
	}

	@PostMapping("/register")
	public String registerUser(@Validated @ModelAttribute("registerForm") RegisterForm form,
			BindingResult bindingResult, Model model, HttpServletRequest request) {

		// 入力項目のバリデーションチェックを行う
		if (bindingResult.hasErrors()) {
			return "login/register";
		}

		// ユーザIDとメールアドレスの重複チェックを行う
		RegisterService.UserCheckResult result = registerService.checkUserExists(form.getUserName(),
				form.getMailAddress());

		if (result == RegisterService.UserCheckResult.FOUND) {
			model.addAttribute("registerError", "ユーザ名またはメールアドレスは既に使用されています。");
			return "login/register";
		}

		// ユーザの新規登録を行う
		registerService.register(form.getUserName(), form.getMailAddress(), form.getPassword());

		// 新規登録したらそのままログインする
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(form.getUserName(),
				form.getPassword());

		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);

		// メニュー画面に遷移する
		return "redirect:/menu";
	}
}
