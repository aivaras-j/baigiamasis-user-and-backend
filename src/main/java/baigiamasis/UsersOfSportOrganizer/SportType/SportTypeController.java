package baigiamasis.UsersOfSportOrganizer.SportType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class SportTypeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/news/sportTypes")
    public String sportTypes(Model model) {
        SportTypes sportTypes = restTemplate.getForObject("http://localhost:8081/news/sportTypes",
                SportTypes.class);
        model.addAttribute("sportTypes", sportTypes.getSportTypes());
        return "event/sportEvents";
    }

    @GetMapping("news/sportType/new")
    public String createNewSportType(Model model) {
        model.addAttribute("sportType", new SportType());
        return "event/newSportType";
    }

    @PostMapping("news/sportType/new")
    public String createNewSportType(@RequestParam("name") String name, Model model) {
        SportType sportType = new SportType();
        sportType.setName(name);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<SportType> requestEntity = new HttpEntity<>(sportType, headers);

        SportType createdSportType = restTemplate.postForObject("http://localhost:8081/news/sportType/new",
                requestEntity, SportType.class);
        logger.info("New SportType created: {}", createdSportType);

        return "redirect:/news/sportTypes";
    }

}
