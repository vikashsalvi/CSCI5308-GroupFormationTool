package com.app.group15.services;

import com.app.group15.config.AppConfig;
import com.app.group15.dao.UserRoleDao;
import com.app.group15.injectors.dao.UserRoleDaoInjectorService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AuthorizationService implements IAuthorizationService{
	
	private Set<String> allowedRoles=new HashSet<>();

	public void setAllowedRoles(String[] args) {
		this.allowedRoles.clear();
		this.allowedRoles.addAll(Arrays.asList(args));
	}

	public Set<String> getAllowedRoles() {
		return allowedRoles;
	}

	public boolean isAuthorized(HttpServletRequest request) {
		String bannerId = (String) request.getSession().getAttribute("BANNER_ID_SESSION");
		UserRoleDao userRoleDao = AppConfig.getInstance().getUserRoleDaoInjectorService().getUserRoleDao();
		Set<String> intersection = new HashSet<>(this.allowedRoles);
		Set<String> roles = userRoleDao.getRolesByBannerId(bannerId);
		intersection.retainAll(roles);
		System.out.println(roles);
		System.out.println(intersection);
		if (intersection.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
