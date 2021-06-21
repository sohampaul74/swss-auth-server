package org.swss.security.service;

import java.util.List;

import org.swss.security.model.ChangePasswordModel;
import org.swss.security.model.ForgorPasswordModel;
import org.swss.security.model.RegistrationModel;

public interface AccessibilityService {

	List<String> registerUser(RegistrationModel regModel);
	List<String> changePassword(ChangePasswordModel cpModel);
	List<String> resetPassword(ForgorPasswordModel fpModel);
	
}
