package org.swss.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.swss.security.ent.User;
import org.swss.security.model.AuthUserDetails;
import org.swss.security.repo.UserRepository;
import org.swss.security.util.AccountDetailsChecker;

@Service
public class LocalUserDetailService implements UserDetailsService {

	public static final String AUTH_SERVICE_USERNAME_NOT_FOUND = "auth.service.username.not-found";
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(AUTH_SERVICE_USERNAME_NOT_FOUND));
		new AccountDetailsChecker().check(user);
		AuthUserDetails authUserDetails = new AuthUserDetails(user);
		return authUserDetails;
	}

}
