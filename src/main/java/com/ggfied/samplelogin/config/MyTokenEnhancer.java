package com.ggfied.samplelogin.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.ggfied.samplelogin.model.Language;
import com.ggfied.samplelogin.model.MyUserDetails;
import com.ggfied.samplelogin.model.User;
import com.ggfied.samplelogin.repository.LanguageRepository;

@Component
@Primary
public class MyTokenEnhancer implements TokenEnhancer {

	@Autowired
	private LanguageRepository languageRepository;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		User user = ((MyUserDetails) authentication.getPrincipal()).getUser();
		Map<String, Object> additionalInformation = new HashMap<>();
		Object[] roles = user.getRoleNames();

		additionalInformation.put("name", user.getName());
		additionalInformation.put("username", user.getUsername());
		additionalInformation.put("roles", roles);

		Language language = user.getLanguage();

		if (language == null) {
			language = this.languageRepository.findById(1L).get();
		}

		additionalInformation.put("language", language.getCode());

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);

		return accessToken;
	}

}
