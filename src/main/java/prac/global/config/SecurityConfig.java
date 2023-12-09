package prac.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import prac.user.service.AuthenticationProviderService;

@Configuration
public class SecurityConfig {

    private final AuthenticationProviderService authenticationProvider;

    public SecurityConfig(AuthenticationManagerBuilder authBuilder,
            AuthenticationProviderService authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
        authBuilder.authenticationProvider(authenticationProvider);
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SCryptPasswordEncoder sCryptPasswordEncoder() {
        return new SCryptPasswordEncoder(1,2,3,4,5);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(
                        AbstractHttpConfigurer::disable
                )
                .formLogin(formLogin ->
                        formLogin
                                .defaultSuccessUrl("/main", true)
                )
                .authorizeHttpRequests(
                        auth -> auth.anyRequest()
                                .authenticated()
                );

        return http.build();
    }
}
