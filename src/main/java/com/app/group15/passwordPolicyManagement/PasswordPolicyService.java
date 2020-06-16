package com.app.group15.passwordPolicyManagement;

import com.app.group15.config.AppConfig;
import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyService implements IPasswordPolicyServiceInjector, IPasswordPolicyService {

	private List<IPasswordPolicyValidator> passwordPolicyList;
	private String message;

	@Override
	public void injectPasswordPolicy(List<IPasswordPolicyValidator> passwordPolicy) {
		this.passwordPolicyList = passwordPolicy;

	}

	@Override
	public PasswordPolicyValidationResult validatePassword(String password, int userId) {

		PasswordPolicyValidationResult result = new PasswordPolicyValidationResult();
		List<String> failList = new ArrayList<String>();

		for (int i = 0; i < passwordPolicyList.size(); i++) {
			if (passwordPolicyList.get(i).getClass().getSimpleName().equals("PasswordPolicyHistoryConstraint")) {
				PasswordPolicyHistoryConstraint passwordPolicy = (PasswordPolicyHistoryConstraint) passwordPolicyList
						.get(i);
				passwordPolicy.setUserId(userId);
				passwordPolicyList.set(i, passwordPolicy);
				
			}
			if (passwordPolicyList.get(i).isPasswordValid(password)) {
				continue;
			} else {
				failList.add(passwordPolicyList.get(i).getClass().getSimpleName());

			}
		}
		if (failList.size() > 0) {
			result.setFailedPolicyList(failList);
			generateFailureMessage(failList);
			result.setResult(false);
			result.setMessage(this.message);
		} else {
			result.setResult(true);
		}
		return result;
	}
	
	private void generateFailureMessage(List<String> failList) {
		this.message="Failed Password Policies are: ";
		failList.forEach((fail)->{
			switch (fail) {
			case "PasswordPolicyCharNotAllowed":
				this.message+=" Char Not Allowed";
				break;
			case "PasswordPolicyHistoryConstraint":
				this.message+=" History Constraint";
				break;
			case "PasswordPolicyMaxLength":
				this.message+=" Max Length";
				break;
			case "PasswordPolicyMinLength":
				this.message+=" Min Length";
				break;
			case "PasswordPolicyMinLowerCase":
				this.message+=" Min Lower Case";
				break;
			case "PasswordPolicyMinSpecialChar":
				this.message+=" Min Special char";
				break;
			case "PasswordPolicyMinUpperCase":
				this.message+=" Min upper case";
				break;
			}
		});
		
	}


}
