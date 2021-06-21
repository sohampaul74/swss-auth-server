package org.swss.security.converter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.swss.security.ent.Role;
import org.swss.security.ent.User;
import org.swss.security.model.RegistrationModel;

@Component
public class RegistrationToUserConverter implements Converter<RegistrationModel, User> {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User convert(RegistrationModel source) {
		User user = new User();
		user.setEmail(source.getEmail());
		user.setUsername(source.getUsername());
		user.setPassword(passwordEncoder.encode(source.getPassword()));
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		return user;
	}

}
