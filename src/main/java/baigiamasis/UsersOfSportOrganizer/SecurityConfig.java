package baigiamasis.UsersOfSportOrganizer;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests().requestMatchers("/style/**", "/resources/**", "/error", "/webjars/**", "/", "/index", "/signup", "/news/**", "/news/articles/**", "/news/articles/new")
                .permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .usernameParameter("username")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/news", true)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/news")
                .permitAll()
                .and()
                .build();
    }

}

