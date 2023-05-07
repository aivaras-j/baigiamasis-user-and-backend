package baigiamasis.UsersOfSportOrganizer.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Optional;


@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String create(User user, Model model) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        logger.info("User saved: {}", user);
        return "redirect:/user/login";
    }

    @GetMapping("/register")
    public String show(Model model) {
        model.addAttribute("user", new User());
        return "/user/register";
    }

    @GetMapping("/user/login")
    public String loginPage(Model model) {

        return "user/login";
    }

    @GetMapping("/news/user/{id}/profile")
    public String profile(@PathVariable int id, Model model) {

        model.addAttribute("user", userRepository.findById(id).get());
        User user = userRepository.findById(id).get();
        logger.info("User", user);
        return "/user/profile";
    }


}



