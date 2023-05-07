package baigiamasis.UsersOfSportOrganizer.Comment;

import baigiamasis.UsersOfSportOrganizer.Article.Article;
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
public class CommentController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("news/article/{id}/comment")
    public String createNewCommentForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("comment", new Comment());

        Article article = restTemplate.getForObject("http://localhost:8081/news/article/" + id, Article.class);
        model.addAttribute("article", article);


        return "comments/comment";
    }

    @PostMapping("/news/article/{id}/comment")
    public String createComment(@PathVariable("id") int id,
                                @RequestParam("name") String name,
                                @RequestParam("content") String content,
                                Model model) {
        Comment comment = new Comment();
        comment.setName(name);
        comment.setContent(content);

        logger.info("New comment created: {}", comment);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Comment> requestEntity = new HttpEntity<>(comment, headers);

        ResponseEntity<Comment> responseEntity = restTemplate.exchange(
                "http://localhost:8081/news/article/{id}/comment",
                HttpMethod.POST,
                requestEntity,
                Comment.class,
                id
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            Comment createdComment = responseEntity.getBody();
            logger.info("New comment created: {}", createdComment);
        } else {
            logger.error("Failed to create comment: {}", responseEntity.getStatusCode());
        }

        return "redirect:/news/article/" + id;
    }

    @PostMapping(value = "/news/article/{id}/commentDelete", params = "_method=DELETE")
    public String deleteComment(@PathVariable("id") int id) {
        restTemplate.delete("http://localhost:8081/news/article/{id}/commentDelete", id);
        logger.info("Comment deleted with ID: {}", id);
        return "redirect:/news";
    }

}
