package org.swss.security;

import static org.assertj.core.api.Assertions.fail;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.swss.security.model.ClientDetail;
import org.swss.security.model.LoginDetail;
import org.swss.security.model.TokenDetail;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.yml",properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
class AuthServerApplicationTests {

	private static final String CLIENT_DETAIL_FAILURE = "client-detail-failure";
	private static final String CLIENT_DETAIL_SUCCESS = "client-detail-success";
	private static final String LOGIN_DETAIL_FAILURE = "login-detail-failure";
	private static final String LOGIN_DETAIL_SUCCESS = "login-detail-success";
	private static Map<String, LoginDetail> userMap;
	private static Map<String, ClientDetail> clientMap;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeAll
	private static void setupVariables() {
		userMap = new HashMap<>();
		clientMap = new HashMap<>();
		
		LoginDetail loginDetailSuccess = new LoginDetail("krish", "123", "password");
		LoginDetail loginDetailFailure = new LoginDetail("krish", "1234", "password");
		ClientDetail clientDetailSuccess = new ClientDetail("mobile", "mobile");
		ClientDetail clientDetailFailure = new ClientDetail("moibile","234");
		userMap.put(LOGIN_DETAIL_SUCCESS, loginDetailSuccess);
		userMap.put(LOGIN_DETAIL_FAILURE, loginDetailFailure);
		clientMap.put(CLIENT_DETAIL_SUCCESS, clientDetailSuccess);
		clientMap.put(CLIENT_DETAIL_FAILURE, clientDetailFailure);
	}
	
	@Test
	void contextLoads() {
		Assertions.assertTrue(true);
	}
	
	@Nested
	class TestLoginSuccessSequence {
		
		private TokenDetail tokenDetail;
		private ObjectMapper objectMapper = new ObjectMapper();
		
		@Test
		void testLoginSucessWithValidClientCredential() {
			ClientDetail cd = clientMap.get(CLIENT_DETAIL_SUCCESS);
			LoginDetail ld = userMap.get(LOGIN_DETAIL_SUCCESS);
			String clientCredential = Base64.getEncoder().encodeToString((cd.getUsername()+":"+cd.getPassword()).getBytes());
			try {
				String output = sendTokenRequest(ld, clientCredential);
				tokenDetail = objectMapper.readValue(output.getBytes(), TokenDetail.class);
				output = sendTokenRequest(ld, clientCredential);
				TokenDetail reTokenDetail = objectMapper.readValue(output.getBytes(), TokenDetail.class);
				System.out.println("tokenDetail:"+objectMapper.writeValueAsString(tokenDetail)+"\nreTokenDetail:"+objectMapper.writeValueAsString(reTokenDetail));
				Assertions.assertEquals(tokenDetail, reTokenDetail);
				Thread.sleep(reTokenDetail.getExpiresIn()*1000);
				String checkTokenValue = checkTokenDetail(tokenDetail, clientCredential);
				Assertions.assertNotEquals(checkTokenValue, null);
				output = sendTokenRequest(ld, clientCredential);
				reTokenDetail = objectMapper.readValue(output.getBytes(), TokenDetail.class);
				System.out.println("tokenDetail:"+objectMapper.writeValueAsString(tokenDetail)+"\nreTokenDetail:"+objectMapper.writeValueAsString(reTokenDetail));
				Assertions.assertEquals(reTokenDetail.getAccessToken(), tokenDetail.getAccessToken());
				Assertions.assertEquals(reTokenDetail.getExpiresIn(), 0);
				output = sendRefreshTokenRequest(tokenDetail, clientCredential);
				reTokenDetail = objectMapper.readValue(output.getBytes(), TokenDetail.class);
				String newTokenValue = checkTokenDetail(reTokenDetail, clientCredential);
				System.out.println("prevTokenDetail:"+checkTokenValue+"\nnewTokenDetail:"+newTokenValue);
				Assertions.assertEquals(objectMapper.readTree(newTokenValue).get("autorities"), objectMapper.readTree(checkTokenValue).get("autorities"));
				
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}

		private String sendTokenRequest(LoginDetail ld, String clientCredential)
				throws UnsupportedEncodingException, Exception {
			String output = mockMvc.perform(MockMvcRequestBuilders
					.post(URI.create("/oauth/token")).header("Authorization", "Basic "+clientCredential)
					.param("grant_type", ld.getGrantType())
					.param("password", ld.getPassword())
					.param("username", ld.getUsername())
					.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
					.andReturn().getResponse().getContentAsString();
			return output;
		}
		
		private String sendRefreshTokenRequest(TokenDetail td, String clientCredential) 
				throws UnsupportedEncodingException, Exception { 
			String output = mockMvc.perform(MockMvcRequestBuilders
					.post(URI.create("/oauth/token")).header("Authorization", "Basic "+clientCredential)
					.param("grant_type", "refresh_token")
					.param("refresh_token", td.getRefreshToken())
					.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
					.andReturn().getResponse().getContentAsString();
			return output;
		}
		
		private String checkTokenDetail(TokenDetail td, String clientCredential) 
				throws UnsupportedEncodingException, Exception {
			String output = mockMvc.perform(MockMvcRequestBuilders
					.get(URI.create("/oauth/check_token")).header("Authorization", "Basic "+clientCredential)
					.queryParam("token", td.getAccessToken()))
					.andReturn().getResponse().getContentAsString();
			return output;
		}
		
	}
	
}
