package org.swss.security.config;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.stereotype.Component;

@Component
public class AppEventListener implements ApplicationListener<AbstractAuthenticationEvent> {
	
	@Override
	public void onApplicationEvent(AbstractAuthenticationEvent event) {
		System.out.println("username: "+event.getAuthentication().getName()+" Login at: "+event.getTimestamp());
	}
}
