package org.swss.security.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.swss.security.model.ChangePasswordModel;
import org.swss.security.model.ForgorPasswordModel;
import org.swss.security.model.RegistrationModel;
import org.swss.security.service.AccessibilityService;

@RestController
@RequestMapping("/user/access")
public class AccessibilityController {
	
	@Autowired
	private AccessibilityService accessService;
	
	@PostMapping(path = "/register",consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<String> registerUser(@RequestBody @Valid RegistrationModel regModel, BindingResult br) {
		if(br.hasErrors()) {
			return br.getAllErrors().parallelStream().map(t->t.getCode()).collect(Collectors.toList());
		}
		return accessService.registerUser(regModel);
	}
	
	@PostMapping(path="/pass/change",consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<String> changePassword(@RequestBody @Valid ChangePasswordModel cpModel, BindingResult br) {
		if(br.hasErrors()) {
			return br.getAllErrors().parallelStream().map(t->t.getCode()).collect(Collectors.toList());
		}
		return accessService.changePassword(cpModel);
	}
	
	@PostMapping(path="/pass/forgot",consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<String> forgotPassword(@RequestBody @Valid ForgorPasswordModel fpModel, BindingResult br) {
		if(br.hasErrors()) {
			return br.getAllErrors().parallelStream().map(t->t.getCode()).collect(Collectors.toList());
		}
		return accessService.resetPassword(fpModel);
	}
	
}
