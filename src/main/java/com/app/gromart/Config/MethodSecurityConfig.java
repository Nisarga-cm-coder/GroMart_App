package com.app.gromart.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity  // Enables @PreAuthorize, @PostAuthorize, etc.
public class MethodSecurityConfig {
    // No additional code needed here unless you want custom settings
}
