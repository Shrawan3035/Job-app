package com.kolekar.spring_boot_rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider(userDetailsService);
//        PasswordEncoder passwordEncoder = passwordEncoder();
//        provider.setPasswordEncoder(passwordEncoder); // Default BCrypt PasswordEncoder
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//     Customizer<CsrfConfigurer<HttpSecurity>> custCsrf = new Customizer<CsrfConfigurer<HttpSecurity>> (){
//
//         @Override
//        public void customize(CsrfConfigurer<HttpSecurity> configurer){
//            configurer.disable();
//      }
//        };
//        http.csrf(custCsrf);
//
//         Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.
//		  AuthorizationManagerRequestMatcherRegistry> custHttp = new Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.
//		  AuthorizationManagerRequestMatcherRegistry>() {
//
//		  @Override
//          public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.
//		  AuthorizationManagerRequestMatcherRegistry registry) {
//		  registry.anyRequest().authenticated();
//
//		  } };
//
//		  http.authorizeHttpRequests(custHttp); http.csrf(custCsrf);
// OR
//        http.csrf(customizer -> customizer.disable());
//        http.authorizeHttpRequests(requests -> requests.anyRequest().authenticated());
//        http.httpBasic(Customizer.withDefaults());
//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

// OR
        http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(requests -> requests.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user = User
//                .withDefaultPasswordEncoder()
//                .username("siddhi")
//                .password("1234")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User
//                .withDefaultPasswordEncoder()
//                .username("shrawan")
//                .password("3035")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }


}
