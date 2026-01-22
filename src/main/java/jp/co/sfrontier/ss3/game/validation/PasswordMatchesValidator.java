package jp.co.sfrontier.ss3.game.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import jp.co.sfrontier.ss3.game.model.ChangePasswordForm;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, ChangePasswordForm> {

	@Override
	public boolean isValid(ChangePasswordForm form, ConstraintValidatorContext context) {
		if (form.getNewPassword() == null || form.getConfirmPassword() == null) {
			return false;
		}
		return form.getNewPassword().equals(form.getConfirmPassword());
	}
}
