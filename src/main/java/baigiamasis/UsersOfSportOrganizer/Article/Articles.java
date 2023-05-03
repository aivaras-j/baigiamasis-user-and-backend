package baigiamasis.UsersOfSportOrganizer.Article;

import baigiamasis.UsersOfSportOrganizer.Article.Article;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Articles {

    @NonNull
    public List<Article> articles;

}