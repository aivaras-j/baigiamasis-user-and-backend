package baigiamasis.UsersOfSportOrganizer.Article;

import baigiamasis.UsersOfSportOrganizer.Comment.Comment;
import lombok.NonNull;

import java.util.List;

public class ArticleWithComments {

    @NonNull
    private int id;
    private Article article;
    private List<Comment> comments;

    public ArticleWithComments() {
    }

    public ArticleWithComments(Article article, List<Comment> comments) {
        this.article = article;
        this.comments = comments;
    }

    public Article getArticle() {
        return article;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}