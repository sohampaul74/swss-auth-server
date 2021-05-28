package org.swss.security.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.swss.security.ent.User;
import org.swss.security.exception.AccountDetailException;
import org.swss.security.model.AuthUserDetails;
import org.swss.security.repo.UserRepository;
import org.swss.security.util.AccountDetailsChecker;

@ExtendWith(MockitoExtension.class)
class LocalUserDetailServiceTest {
	
	private static Map<String, User> dataMap;
	private static AuthUserDetails authUserDetails;

	private UserRepository userRepository = Mockito.mock(UserRepository.class);
	
	@InjectMocks
	private LocalUserDetailService userDetailService;
	
	@BeforeAll
	private static void setup() {
		dataMap = new HashMap<>();
		User user = new User(1, "success", "", "", true, true, true, true, Collections.emptySet());
		User user2 = new User(2,"account-disabled","","",false,true,true,true,Collections.emptySet());
		User user3 = new User(3,"account-expired","","",true,false,true,true,Collections.emptySet());
		User user4 = new User(4,"credential-expired","","",true,true,false,true,Collections.emptySet());
		User user5 = new User(5,"account-locked","","",true,true,true,false,Collections.emptySet());
		dataMap.put(user.getUsername(), user);
		dataMap.put(user2.getUsername(), user2);
		dataMap.put(user3.getUsername(), user3);
		dataMap.put(user4.getUsername(), user4);
		dataMap.put(user5.getUsername(), user5);
		dataMap = Collections.unmodifiableMap(dataMap);
		authUserDetails = new AuthUserDetails(user);
	}
	
	private void setupRepo(String username) {
		Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.ofNullable(dataMap.get(username)));
	}
	
	@Test
	void testLoadUserByUsernameSuccess() {
		String username = "success";
		setupRepo(username);
		assertThat(userDetailService.loadUserByUsername(username)).isEqualTo(authUserDetails);
	}
	
	@Test
	void testLoadUserByUsernameNotFoundException() {
		String username = "not-present";
		setupRepo(username);
		Assertions.assertThrows(UsernameNotFoundException.class, () -> userDetailService.loadUserByUsername(username), LocalUserDetailService.AUTH_SERVICE_USERNAME_NOT_FOUND);
	}
	
	@Test
	void testLoadUserByUsernameAccountDisabled() {
		String username = "account-disabled";
		setupRepo(username);
		Assertions.assertThrows(AccountDetailException.class, ()-> userDetailService.loadUserByUsername(username), AccountDetailsChecker.AUTH_DETAIL_ERROR_ACCOUNT_DISABLED);
	}
	
	@Test
	void testLoadUserByUsernameAccountExpired() {
		String username = "account-expired";
		setupRepo(username);
		Assertions.assertThrows(AccountDetailException.class, ()-> userDetailService.loadUserByUsername(username), AccountDetailsChecker.AUTH_DETAIL_ERROR_ACCOUNT_EXPIRED);
	}
	
	@Test
	void testLoadUserByUsernameAccountCredentialsExpired() {
		String username = "credential-expired";
		setupRepo(username);
		Assertions.assertThrows(AccountDetailException.class, ()-> userDetailService.loadUserByUsername(username), AccountDetailsChecker.AUTH_DETAIL_ERROR_ACCOUNT_CREDENTIAL_EXPIRED);
	}
	
	@Test
	void testLoadUserByUsernameAccountLocked() {
		String username = "account-locked";
		setupRepo(username);
		Assertions.assertThrows(AccountDetailException.class, ()-> userDetailService.loadUserByUsername(username), AccountDetailsChecker.AUTH_DETAIL_ERROR_ACCOUNT_LOCKED);
	}
}
