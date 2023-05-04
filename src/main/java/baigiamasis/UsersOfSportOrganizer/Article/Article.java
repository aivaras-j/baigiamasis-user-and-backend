package baigiamasis.UsersOfSportOrganizer.Article;

import baigiamasis.UsersOfSportOrganizer.Comment.Comment;
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


}
