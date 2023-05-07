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
    private String team1;

    @NonNull
    private String team2;

    public Event(@NonNull String name, @NonNull String place, @NonNull String team1, @NonNull String team2) {
        this.name = name;
        this.place = place;
        this.team1 = team1;
        this.team2 = team2;
    }
}