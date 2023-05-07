package baigiamasis.UsersOfSportOrganizer.Article;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class ArticleController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/news/article/{id}")
    public String articleById(@PathVariable int id, Model model) {
        ArticleWithComments articleWithComments = restTemplate.getForObject("http://localhost:8081/news/article/" + id, ArticleWithComments.class);
        model.addAttribute("articleWithComments", articleWithComments);

        logger.info("articleWithComments: {}", articleWithComments);

        Article article = restTemplate.getForObject("http://localhost:8081/news/article/" + id, Article.class);
        model.addAttribute("article", article);

        logger.info("article: {}", article);

        return "article/article";
    }

    @GetMapping("/news/article/{id}/edit")
    public String showArticleEdit(@PathVariable("id") int id, Model model) {

        Article article = restTemplate.getForObject("http://localhost:8081/news/article/" + id, Article.class);
        model.addAttribute("article", article);

        return "article/articleEdit";
    }

    @GetMapping("/news")
    public String articles(Model model) {
        Articles articles = restTemplate.getForObject("http://localhost:8081/news", Articles.class);
        model.addAttribute("articles", articles.getArticles());
        return "article/news";
    }

    @GetMapping("news/article/new")
    public String createNewArticle(Model model) {
        model.addAttribute("article", new Article());
        return "article/newArticle";
    }

    @PostMapping("news/article/new")
    public String createArticle(@RequestParam("title") String title,
                                @RequestParam("imageUrl") String imageUrl,
                                @RequestParam("text") String text,
                                Model model) {
        Article article = new Article();
        article.setTitle(title);
        article.setImageUrl(imageUrl);
        article.setText(text);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Article> requestEntity = new HttpEntity<>(article, headers);

        Article createdArticle = restTemplate.postForObject("http://localhost:8081/news/article/new", requestEntity, Article.class);
        logger.info("New article created: {}", createdArticle);

        return "redirect:/news";
    }

    @PostMapping("/news/article/{id}/edit")
    public String editArticle(@PathVariable("id") int id, @ModelAttribute("article") Article updatedArticle, Model model) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpEntity<Article> requestEntity = new HttpEntity<>(updatedArticle, headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                "http://localhost:8081/news/article/{id}/edit",
                HttpMethod.POST,
                requestEntity,
                Void.class,
                id
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            logger.info("Article updated: {}", updatedArticle);
        } else {
            logger.error("Failed to update article");
        }

        return "redirect:/news";
    }

    @PostMapping(value = "/news/article/{id}", params = "_method=DELETE")
    public String deleteArticle(@PathVariable("id") int id) {
        restTemplate.delete("http://localhost:8081/news/article/{id}", id);
        logger.info("Article deleted with ID: {}", id);
        return "redirect:/news";
    }

}
