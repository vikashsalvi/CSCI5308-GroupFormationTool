package com.app.group15.UserManagement.SessionManagement;

import com.app.group15.ExceptionHandler.AllowedRolesNotSetException;
import com.app.group15.UserManagement.UserRoleDao;
import com.app.group15.Utility.GroupFormationToolLogger;
import com.app.group15.Utility.ServiceUtility;
import com.app.group15.config.AppConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

public class AuthorizationService implements IAuthorizationService {

	private Set<String> allowedRoles = new HashSet<>();

	public void setAllowedRoles(String[] args) {
		if (ServiceUtility.isNotNull(args)) {
			this.allowedRoles.clear();
			this.allowedRoles.addAll(Arrays.asList(args));
		} else {
			GroupFormationToolLogger.log(Level.SEVERE, "Allowed roles are null ");
		}
	}

	public Set<String> getAllowedRoles() {
		return allowedRoles;
	}

	public boolean isAuthorized(HttpServletRequest request) {
		if (allowedRoles == null) {
			try {
				throw new AllowedRolesNotSetException("allowedRoles parameter not set");
			} catch (AllowedRolesNotSetException e) {
				GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		String bannerId = (String) request.getSession().getAttribute("BANNER_ID_SESSION");
		UserRoleDao userRoleDao = AppConfig.getInstance().getUserRoleDaoInjectorService().getUserRoleDao();
		Set<String> intersection = new HashSet<>(this.allowedRoles);
		Set<String> roles = userRoleDao.getRolesByBannerId(bannerId);
		intersection.retainAll(roles);
		if (intersection.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
}