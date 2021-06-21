package org.swss.security.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.swss.security.ent.Role;
import org.swss.security.ent.User;
import org.swss.security.model.ChangePasswordModel;
import org.swss.security.model.ForgorPasswordModel;
import org.swss.security.model.RegistrationModel;
import org.swss.security.repo.RoleRepository;
import org.swss.security.repo.UserRepository;
import org.swss.security.service.AccessibilityService;

@Service
public class AccessibilirtServiceImpl implements AccessibilityService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private Converter<RegistrationModel, User> userConverterFromRegistration;
	//private Converter<ChangePasswordModel, User> userConverterFromChangePassword;
	//private Converter<ForgorPasswordModel, User> userConverterFromForgotPassword;
	
	@Override
	public List<String> registerUser(RegistrationModel regModel) {
		Callable<User> cUser = () -> userConverterFromRegistration.convert(regModel);
		List<String> status = new ArrayList<>();
		if(userRepository.findByUsername(regModel.getUsername()).isPresent()) {
			status.add("Username already present.");
			return status;
		}
		if(userRepository.findByEmail(regModel.getEmail()).isPresent()) {
			status.add("Email already present.");
			return status;
		}
		try {
			Optional<Role> role = roleRepository.findById(2);
			User u = null;
			if(role.isPresent()) {
				u = cUser.call();
				Set<Role> roles = new HashSet<>();
				roles.add(role.get());
				u.setRoles(Collections.unmodifiableSet(roles));
			}
			userRepository.saveAndFlush(u);
			status.add("SUCCESS");
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Unknown error");
			return status;
		}
	}

	@Override
	public List<String> changePassword(ChangePasswordModel cpModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> resetPassword(ForgorPasswordModel fpModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
