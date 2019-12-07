package com.ggfied.samplelogin.config;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    // Just For Bean
}
