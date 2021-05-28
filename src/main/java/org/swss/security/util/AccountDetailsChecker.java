package org.swss.security.util;

import org.swss.security.ent.User;
import org.swss.security.exception.AccountDetailException;

public class AccountDetailsChecker {

	public static final String AUTH_DETAIL_ERROR_ACCOUNT_DISABLED = "auth.detail.error.account.disabled";
	public static final String AUTH_DETAIL_ERROR_ACCOUNT_CREDENTIAL_EXPIRED = "auth.detail.error.account.credential.expired";
	public static final String AUTH_DETAIL_ERROR_ACCOUNT_LOCKED = "auth.detail.error.account.locked";
	public static final String AUTH_DETAIL_ERROR_ACCOUNT_EXPIRED = "auth.detail.error.account.expired";

	public void check(User user) {
		if(!user.isAccountNonExpired()) {
			throw new AccountDetailException(AUTH_DETAIL_ERROR_ACCOUNT_EXPIRED,user.getUsername());
		}
		if(!user.isAccountNonLocked()) {
			throw new AccountDetailException(AUTH_DETAIL_ERROR_ACCOUNT_LOCKED,user.getUsername());
		}
		if(!user.isCredentialsNonExpired()) {
			throw new AccountDetailException(AUTH_DETAIL_ERROR_ACCOUNT_CREDENTIAL_EXPIRED, user.getUsername());
		}
		if(!user.isEnabled()) {
			throw new AccountDetailException(AUTH_DETAIL_ERROR_ACCOUNT_DISABLED,user.getUsername());
		}
	}

}
