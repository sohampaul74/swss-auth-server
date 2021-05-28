package org.swss.security.model;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUserDetails extends User implements UserDetails {
	private static final long serialVersionUID = -3227965721849169501L;
	
	public AuthUserDetails(org.swss.security.ent.User user) {
		super(
				user.getUsername(), 
				user.getPassword(),
				user.getRoles().parallelStream()
					.map(t->t.getName())
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toSet())
				);
	}
}
