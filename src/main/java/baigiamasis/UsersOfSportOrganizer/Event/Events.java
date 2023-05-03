package baigiamasis.UsersOfSportOrganizer.Event;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Events {
    private List<Event> events;

    public Events(List<Event> events) {
        this.events = events;
    }
}