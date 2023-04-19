package board.website.config;

import board.website.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().cors().disable()
                .authorizeHttpRequests(reqeust ->
                    reqeust.requestMatchers("/member/**", "/getInitData").permitAll()
                            .anyRequest().authenticated()
                );

        httpSecurity.addFilterAfter(jwtFilter, CorsFilter.class);
        return httpSecurity.build();
    }
}
