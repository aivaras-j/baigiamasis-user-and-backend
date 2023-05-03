package baigiamasis.UsersOfSportOrganizer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry){

        // PATH: /xxx/yyy : VIEW (Html): admin/dashboard.html
      //  registry.addViewController("").setViewName("/article/news");
        registry.addViewController("/").setViewName("/article/news");
        registry.addViewController("/user/login").setViewName("/user/login");
       // registry.addViewController("/news").setViewName("article/news");
        //registry.addViewController("/login").setViewName("index");
        registry.addViewController("/admin/dashboard").setViewName("admin/dashboard");
        registry.addViewController("/home").setViewName("home");
    }
}