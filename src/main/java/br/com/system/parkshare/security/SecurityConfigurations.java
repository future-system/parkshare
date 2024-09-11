package br.com.system.parkshare.security;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfigurations {

    @Value("${security.public.key}")
    private RSAPublicKey publicKey;

    @Value("${security.private.key}")
    private RSAPrivateKey privateKey;

    // @Autowired
    // private AccountService accountService;

    @Autowired
    private FilterSecurity filterSecurity;

    // @Autowired
    // private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(t -> t.disable()).httpBasic(t -> t.disable()).formLogin(t -> t.disable())
                .authorizeHttpRequests(
                        t -> {
                            t.requestMatchers("/auth/**").permitAll()
                                    .anyRequest().authenticated();
                            // t.requestMatchers("/admin/**").hasRole("ADMIN")
                            // .requestMatchers("/app/**").hasRole("APP")
                            // .requestMatchers("/company/**").hasRole("COMPANY")
                            // .requestMatchers("/establishment/**").hasRole("ESTABLISHMENT")
                            // .requestMatchers("/staff/**").hasRole("STAFF")
                            //.requestMatchers("/v1/**").hasRole("USER");
                            // .requestMatchers("/guest/**").hasRole("GUEST")
                            // .requestMatchers("/none/**").hasRole("NONE");
                            // t.anyRequest().authenticated();

                            // t.anyRequest().permitAll();
                        })
                .oauth2ResourceServer(t -> t.jwt(Customizer.withDefaults()))
                // .authenticationProvider(authenticationProvider)
                .addFilterBefore(filterSecurity, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .logout(logout -> logout.logoutUrl("/v1/auth/logout")
                        .logoutSuccessHandler(
                                (request, response, authentication) -> SecurityContextHolder.clearContext()))
                .build();

    }
    
    



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public JwtDecoder jwtDencoder() {
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(
                new ImmutableJWKSet<>(new JWKSet(new RSAKey.Builder(publicKey).privateKey(privateKey).build())));
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // static RoleHierarchy roleHierarchy() {
    // return RoleHierarchyImpl.withDefaultRolePrefix()
    // .role("ADMIN").implies("APP")
    // .role("APP").implies("COMPANY")
    // .role("COMPANY").implies("ESTABLISHMENT")
    // .role("ESTABLISHMENT").implies("STAFF")
    // .role("ESTABLISHMENT").implies("USER")
    // .role("USER").implies("GUEST")
    // .role("GUEST").implies("NONE")
    // .build();
    // }

    // @Bean
    // static MethodSecurityExpressionHandler
    // methodSecurityExpressionHandler(RoleHierarchy roleHierarchy) {
    // DefaultMethodSecurityExpressionHandler expressionHandler = new
    // DefaultMethodSecurityExpressionHandler();
    // expressionHandler.setRoleHierarchy(roleHierarchy);
    // return expressionHandler;
    // }
}
