package com.roadmap.stackoverclone.configuration;

import com.roadmap.stackoverclone.constant.RoleConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private ResourceServerTokenServices tokenServices;

    @Value("${security.jwt.resource-ids}")
    private String resourceIds;

    @Value("${spring.h2.console.path}")
    private String consolePath;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceIds).tokenServices(tokenServices);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin() // For h2-console to work
                .and()
                .requestMatchers()
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/actuator/**",
                        "/v2/api-docs/**",
                        "/oauth/token/**",
                        consolePath + "/**"
                ).permitAll()
                // TODO: allow GET requests by unauthorized users
                .anyRequest().hasAuthority(RoleConstants.USER) // Each request should by done by at least ROLE_USER
        ;
    }
}
