package baigiamasis.UsersOfSportOrganizer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("/article/news");
        registry.addViewController("/user/login").setViewName("/user/login");
        registry.addViewController("/register").setViewName("/user/register");
    }
}