package baigiamasis.UsersOfSportOrganizer.Comment;

import baigiamasis.UsersOfSportOrganizer.Article.Article;
import baigiamasis.UsersOfSportOrganizer.SportType.SportType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Comment {

    @NonNull
    private int id;


    @NonNull
    private String name;


    @NonNull
    private String content;

    public Comment(@NonNull String name, @NonNull String content) {
        this.name = name;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }
}