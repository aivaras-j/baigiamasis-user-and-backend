package baigiamasis.UsersOfSportOrganizer.Comment;

import baigiamasis.UsersOfSportOrganizer.Event.Event;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Comments {
    private List<Comment> comments;

    public Comments(List<Comment> comments) {
        this.comments = comments;
    }
}