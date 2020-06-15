package com.app.group15.passwordPolicy;

import com.app.group15.config.AppConfig;
import com.app.group15.dao.PasswordPolicyAbstractDao;
import com.app.group15.dao.PasswordPolicyDao;
import com.app.group15.model.PasswordPolicy;

import java.util.List;

public class PasswordPolicyMinSpecialChar implements IPasswordPolicyValidator{

	@Override
	public boolean isPasswordValid(String password) {

		PasswordPolicyAbstractDao passwordPolicyDao = AppConfig.getInstance().getPasswordPolicyDao();
		List<PasswordPolicy> passwordPolicyList = passwordPolicyDao.getAll();

		int minimumNumberOfSpecialCharAllowed = Integer.parseInt(passwordPolicyList.get(4).getPolicyValue());

		int countSpecialChar=0;

		for (int i =0; i < password.length(); i++ )
		{
			char c = password.charAt(i);

			if (!Character.isDigit(c) && !Character.isLetter(c) && !Character.isWhitespace(c))
			{
				countSpecialChar++;
			}
		}

		if (countSpecialChar >= minimumNumberOfSpecialCharAllowed){
			return true;
		}else {
			return false;
		}
	}

}
