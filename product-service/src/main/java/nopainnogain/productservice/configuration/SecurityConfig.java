package nopainnogain.productservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter filter;

    public SecurityConfig(JwtFilter filter) {
        this.filter = filter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests((auth) ->
                        auth
                                .requestMatchers(HttpMethod.GET, "/product").permitAll()
                                .requestMatchers(HttpMethod.POST, "/product").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/product/**").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/recipe").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/recipe/**").hasAnyAuthority("ADMIN", "USER")
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
