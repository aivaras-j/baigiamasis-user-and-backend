package baigiamasis.UsersOfSportOrganizer.user;

import baigiamasis.UsersOfSportOrganizer.Article.Article;
import baigiamasis.UsersOfSportOrganizer.Article.ArticleWithComments;
import baigiamasis.UsersOfSportOrganizer.Article.Articles;
//import baigiamasis.UsersOfSportOrganizer.Comment.Comment;
//import baigiamasis.UsersOfSportOrganizer.Comment.Comments;
import baigiamasis.UsersOfSportOrganizer.Comment.Comment;
import baigiamasis.UsersOfSportOrganizer.Event.Event;
import baigiamasis.UsersOfSportOrganizer.Event.Events;
import baigiamasis.UsersOfSportOrganizer.SportType.SportType;
import baigiamasis.UsersOfSportOrganizer.SportType.SportTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
public class UserController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WebClient.Builder webClientBuilder;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public String create(User user, Model model) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        logger.info("User saved: {}", user);
        return "redirect:/index";
    }

    @GetMapping("/signup")
    public String show(Model model) {
        model.addAttribute("user", new User());
        return "/signup";
    }

    @GetMapping("/user/login")
    public String loginPage(Model model) {

        return "user/login";
    }

    @GetMapping("/user/{id}")
    public String view(@PathVariable("id") Integer id, Model model) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("user", user);
            return "/profile";
        } else {
            return "redirect:/index";
        }
    }

///////////////

//    @GetMapping("/news/article/{id}")
//    public String articleById(@PathVariable int id, Model model) {
//
//        Article article = restTemplate.getForObject("http://localhost:8081/news/article/" + id, Article.class);
//        model.addAttribute("article", article);
//        return "article/article";
//    }

//    @GetMapping("/news/article/{id}")
//    public String articleById(@PathVariable int id, Model model) {
//        ArticleWithComments articleWithComments = restTemplate.getForObject("http://localhost:8081/news/article/" + id, ArticleWithComments.class);
//        model.addAttribute("articleWithComments", articleWithComments);
//
//        logger.info("articleWithComments: {}", articleWithComments);
//
//        Article article = restTemplate.getForObject("http://localhost:8081/news/article/" + id, Article.class);
//        model.addAttribute("article", article);
//
//        logger.info("article: {}", article);
//
//        return "article/article";
//    }

//    @GetMapping("/news/article/{id}/edit")
//    public String showArticleEdit(@PathVariable("id") int id, Model model) {
//
//        Article article = restTemplate.getForObject("http://localhost:8081/news/article/" + id, Article.class);
//        model.addAttribute("article", article);
//
//        return "article/articleEdit";
//    }


//
//    @GetMapping("news/article/{id}/comment")
//    public String createNewCommentForm(@PathVariable("id") int id, Model model) {
//        model.addAttribute("comment", new Comment());
//
//        Article article = restTemplate.getForObject("http://localhost:8081/news/article/" + id, Article.class);
//        model.addAttribute("article", article);
//
//
//        return "comments/comment";
//    }
//
//    @PostMapping("/news/article/{id}/comment")
//    public String createComment(@PathVariable("id") int id, @RequestParam("name") String name,
//                                @RequestParam("content") String content,
//                                Model model) {
//        Comment comment = new Comment();
//        comment.setName(name);
//        comment.setContent(content);
//
//        logger.info("New comment created: {}", comment);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Comment> requestEntity = new HttpEntity<>(comment, headers);
//
//        ResponseEntity<Comment> responseEntity = restTemplate.exchange(
//                "http://localhost:8081/news/article/{id}/comment",
//                HttpMethod.POST,
//                requestEntity,
//                Comment.class,
//                id
//        );
//
//        if (responseEntity.getStatusCode().is2xxSuccessful()) {
//            Comment createdComment = responseEntity.getBody();
//            logger.info("New comment created: {}", createdComment);
//        } else {
//            logger.error("Failed to create comment: {}", responseEntity.getStatusCode());
//        }
//
//        return "redirect:/news/article/" + id;
//    }


//    @GetMapping("/news")
//    public String articles(Model model) {
//        Articles articles = restTemplate.getForObject("http://localhost:8081/news", Articles.class);
//        model.addAttribute("articles", articles.getArticles());
//        return "article/news";
//    }

//    @GetMapping("news/article/new")
//    public String createNewArticle(Model model) {
//        model.addAttribute("article", new Article());
//        return "article/newArticle";
//    }

//    @PostMapping("news/article/new")
//    public String createArticle(@RequestParam("title") String title,
//                                @RequestParam("imageUrl") String imageUrl,
//                                @RequestParam("text") String text,
//                                Model model) {
//        Article article = new Article();
//        article.setTitle(title);
//        article.setImageUrl(imageUrl);
//        article.setText(text);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Type", "application/json");
//        HttpEntity<Article> requestEntity = new HttpEntity<>(article, headers);
//
//        Article createdArticle = restTemplate.postForObject("http://localhost:8081/news/article/new", requestEntity, Article.class);
//        logger.info("New article created: {}", createdArticle);
//
//        return "redirect:/news";
//    }
//
//    @PostMapping("/news/article/{id}/edit")
//    public String editArticle(@PathVariable("id") int id, @ModelAttribute("article") Article updatedArticle, Model model) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//
//        HttpEntity<Article> requestEntity = new HttpEntity<>(updatedArticle, headers);
//
//        ResponseEntity<Void> responseEntity = restTemplate.exchange(
//                "http://localhost:8081/news/article/{id}/edit",
//                HttpMethod.POST,
//                requestEntity,
//                Void.class,
//                id
//        );
//
//        if (responseEntity.getStatusCode() == HttpStatus.OK) {
//            logger.info("Article updated: {}", updatedArticle);
//        } else {
//            logger.error("Failed to update article");
//        }
//
//        return "redirect:/news";
//    }


//    @GetMapping("/news/sportTypes")
//    public String sportTypes(Model model) {
//        SportTypes sportTypes = restTemplate.getForObject("http://localhost:8081/news/sportTypes", SportTypes.class);
//        model.addAttribute("sportTypes", sportTypes.getSportTypes());
//        return "event/sportEvents";
//    }
//
//
//
//    @GetMapping("/news/sportType/{id}")
//    public String sportTypeById(@PathVariable int id, Model model) {
//        SportType sportType = restTemplate.getForObject("http://localhost:8081/news/sportType/" + id, SportType.class);
//        model.addAttribute("sportType", sportType);
//        model.addAttribute("event", new Event());
//        return "event/newEvent";
//    }
//
//    @GetMapping("news/sportType/new")
//    public String createNewSportType(Model model) {
//        model.addAttribute("sportType", new SportType());
//        return "event/newSportType";
//    }
//
//    @PostMapping("news/sportType/new")
//    public String createNewSportType(@RequestParam("name") String name, Model model) {
//        SportType sportType = new SportType();
//        sportType.setName(name);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Type", "application/json");
//        HttpEntity<SportType> requestEntity = new HttpEntity<>(sportType, headers);
//
//        SportType createdSportType= restTemplate.postForObject("http://localhost:8081/news/sportType/new", requestEntity, SportType.class);
//        logger.info("New SportType created: {}", createdSportType);
//
//        return "redirect:/news";
//    }
//
//    @GetMapping("/news/sportType/{id}/newEvent")
//    public String createNewEvent(Model model) {
//        model.addAttribute("event", new Event());
//        model.addAttribute("sportType", new SportType());
//        return "event/newEvent";
//    }
//
//    @PostMapping("/news/sportType/newEvent")
//    public String createNewEvent(@RequestParam("id") int id, @ModelAttribute("event") Event event, Model model) {
//        SportType sportType = restTemplate.getForObject("http://localhost:8081/news/sportType/" + id, SportType.class);
//        event.setSportType(sportType);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<Event> requestEntity = new HttpEntity<>(event, headers);
//
//        ResponseEntity<Void> responseEntity = restTemplate.exchange(
//                "http://localhost:8081/news/sportType/newEvent",
//                HttpMethod.POST,
//                requestEntity,
//                Void.class
//        );
//
//        if (responseEntity.getStatusCode() == HttpStatus.OK) {
//            logger.info("New event created: {}", event);
//        } else {
//            logger.error("Failed to create event");
//        }
//
//        return "redirect:/news";
//    }

//    @PostMapping(value = "/news/article/{id}", params = "_method=DELETE")
//    public String deleteArticle(@PathVariable("id") int id) {
//        restTemplate.delete("http://localhost:8081/news/article/{id}", id);
//        logger.info("Article deleted with ID: {}", id);
//        return "redirect:/news";
//    }



}



