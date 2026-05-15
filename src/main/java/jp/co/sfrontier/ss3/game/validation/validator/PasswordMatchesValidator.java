package jp.co.sfrontier.ss3.game.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import jp.co.sfrontier.ss3.game.dto.auth.ChangePasswordForm;
import jp.co.sfrontier.ss3.game.validation.annotation.PasswordMatches;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, ChangePasswordForm> {

	@Override
	public boolean isValid(ChangePasswordForm form, ConstraintValidatorContext context) {
		if (form.getNewPassword() == null || form.getConfirmPassword() == null) {
			return false;
		}
		return form.getNewPassword().equals(form.getConfirmPassword());
	}
}
