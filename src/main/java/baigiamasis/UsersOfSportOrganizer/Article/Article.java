package baigiamasis.UsersOfSportOrganizer.Article;

//import baigiamasis.UsersOfSportOrganizer.Comment.Comment;
import baigiamasis.UsersOfSportOrganizer.Comment.Comment;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Article {
    @NonNull
    private int id;

    @NonNull
    private String title;

    @NonNull
    private String imageUrl;

    @NonNull
    private String text;

    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
