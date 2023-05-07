package baigiamasis.UsersOfSportOrganizer;

import baigiamasis.UsersOfSportOrganizer.user.CustomUserDetailsService;
import baigiamasis.UsersOfSportOrganizer.user.User;
import baigiamasis.UsersOfSportOrganizer.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Set;

@SpringBootApplication
public class UsersOfSportOrganizerApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;


    public UsersOfSportOrganizerApplication(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public static void main(String[] args) {
        SpringApplication.run(UsersOfSportOrganizerApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(new CustomUserDetailsService(userRepository));
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService inMemoryUserDetailsService() {
        UserDetails min = org.springframework.security.core.userdetails.User
                .withUsername("aa")
                .password(passwordEncoder().encode("aa"))
                .roles("USER")
                .build();

        UserDetails sup = org.springframework.security.core.userdetails.User
                .withUsername("ss")
                .password(passwordEncoder().encode("ss"))
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(min, sup);
    }

    @Override
    public void run(String... args) {
//		Set<User> users = Set.of(
//				new User("user", passwordEncoder().encode("user"), "USER"),
//				new User("admin", passwordEncoder().encode("admin"), "ADMIN")
//		);
//		userRepository.saveAll(users);
//		logger.info("Users created: {}", users);


    }


}
