package baigiamasis.UsersOfSportOrganizer.Event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Event {

    @NonNull
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String place;

    @NonNull
    private String team;


    public Event(@NonNull String name, @NonNull String place, @NonNull String team) {
        this.name = name;
        this.place = place;
        this.team = team;
    }
}