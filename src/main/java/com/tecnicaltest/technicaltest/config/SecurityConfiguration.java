package com.tecnicaltest.technicaltest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Autowired
  private AuthenticationProvider authenticationProvider;
  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;
  private static final String API_URL_PATTERN = "/auth/**";

//  @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
//    MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
//    http.csrf(csrfConfigurer -> csrfConfigurer.ignoringRequestMatchers(mvcMatcherBuilder.pattern(API_URL_PATTERN), PathRequest.toH2Console()));
//    http.headers(headersConfigurer -> headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
//    http.authorizeHttpRequests(auth -> auth.requestMatchers(mvcMatcherBuilder.pattern(API_URL_PATTERN)).permitAll().requestMatchers(PathRequest.toH2Console()).authenticated().anyRequest().authenticated()).authenticationProvider(authenticationProvider).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//    http.formLogin(Customizer.withDefaults());
//    http.httpBasic(Customizer.withDefaults());
//    return http.build();
//  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrfConfigurer -> csrfConfigurer.ignoringRequestMatchers(
            new AntPathRequestMatcher(API_URL_PATTERN), new AntPathRequestMatcher("/h2-console/**")))
        .headers(headersConfigurer -> headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(new AntPathRequestMatcher(API_URL_PATTERN)).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).authenticated()
            .anyRequest().authenticated()
        )
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .formLogin(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults());

    return http.build();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("http://localhost:8005"));
    configuration.setAllowedMethods(List.of("GET", "POST"));
    configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }

}
