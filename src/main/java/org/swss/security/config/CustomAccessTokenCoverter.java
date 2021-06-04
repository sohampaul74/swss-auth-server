package org.swss.security.config;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessTokenCoverter extends DefaultAccessTokenConverter {
	
	private final String AUDIENCE = "aud";
	private final String CLIENT_ID = "client_id";
	private final String EXPIRES_AT = "exp";
	private final String USERNAME = "username";
	private final String TOKEN_TYPE = "token_type";
	private final String ISSUED_AT = "iat";
	private final String ISSUER = "iss";
	private final String NOT_BEFORE = "nbf";
	private final String SUBJECT = "sub";
	private final String SCOPE = "scope";
	
	/**
			claims.put(AUDIENCE, Collections.unmodifiableList(audiences));
			claims.put(CLIENT_ID, response.getClientID().getValue());
			claims.put(EXPIRES_AT, exp);
			claims.put(ISSUED_AT, iat);
			claims.put(ISSUER, issuer(response.getIssuer().getValue()));
			claims.put(NOT_BEFORE, response.getNotBeforeTime().toInstant());
			claims.put(SCOPE, scopes);
	 */
	@Override
	public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
		Map<String, Object> tokenDetails = new HashMap<>();
		tokenDetails.put(AUDIENCE, authentication.getOAuth2Request().getResourceIds().toArray());
		tokenDetails.put(CLIENT_ID, authentication.getOAuth2Request().getClientId());
		tokenDetails.put(USERNAME, authentication.getUserAuthentication().getPrincipal());
		tokenDetails.put(SCOPE, authentication.getAuthorities().parallelStream().map(t->t.getAuthority()).reduce((q,r)->q+","+r));
		return tokenDetails;
	}
	
}
