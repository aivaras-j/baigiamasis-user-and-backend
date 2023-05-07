package baigiamasis.UsersOfSportOrganizer.Event;

import baigiamasis.UsersOfSportOrganizer.SportType.SportType;
import baigiamasis.UsersOfSportOrganizer.SportType.SportTypeWithEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class EventController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/news/sportType/{id}")
    public String sportTypeById(@PathVariable int id, Model model) {
        SportTypeWithEvents sportTypeWithEvents = restTemplate.getForObject("http://localhost:8081/news/sportType/"
                + id, SportTypeWithEvents.class);
        model.addAttribute("sportTypeWithEvents", sportTypeWithEvents);

        SportType sportType = restTemplate.getForObject("http://localhost:8081/news/sportType/" + id,
                SportType.class);
        model.addAttribute("sportType", sportType);
        return "event/events";
    }

    @GetMapping("/news/sportType/{id}/newEvent")
    public String createNewEvent(@PathVariable("id") int id, Model model) {
        model.addAttribute("event", new Event());

        SportType sportType = restTemplate.getForObject("http://localhost:8081/news/sportType/" + id,
                SportType.class);
        model.addAttribute("sportType", sportType);
        return "event/newEvent";
    }

    @PostMapping("/news/sportType/{id}/newEvent")
    public String createNewEvent(@PathVariable("id") int id,
                                 @RequestParam("name") String name,
                                 @RequestParam("place") String place,
                                 @RequestParam("team1") String team1,
                                 @RequestParam("team2") String team2,
                                 Model model) {

        logger.info("New id created: {}", id);
        Event event = new Event();
        event.setName(name);
        event.setPlace(place);
        event.setTeam1(team1);
        event.setTeam2(team2);

        logger.info("New event created: {}", event);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Event> requestEntity = new HttpEntity<>(event, headers);

        ResponseEntity<Event> responseEntity = restTemplate.exchange(
                "http://localhost:8081/news/sportType/{id}/newEvent",
                HttpMethod.POST,
                requestEntity,
                Event.class,
                id
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            Event createdEvent = responseEntity.getBody();
            logger.info("New event created: {}", createdEvent);
        } else {
            logger.error("Failed to create event: {}", responseEntity.getStatusCode());
        }

        return "redirect:/news/sportType/" + id;
    }

    @PostMapping(value = "/news/sportType/{id}", params = "_method=DELETE")
    public String deleteEvent(@PathVariable("id") int id) {
        restTemplate.delete("http://localhost:8081/news/sportType/{id}", id, Event.class);
        logger.info("Event deleted with ID: {}", id);
        return "redirect:/news/sportType/" + id;
    }


}
