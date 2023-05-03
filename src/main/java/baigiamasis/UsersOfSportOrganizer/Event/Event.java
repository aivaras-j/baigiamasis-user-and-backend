package baigiamasis.UsersOfSportOrganizer.Event;

import baigiamasis.UsersOfSportOrganizer.SportType.SportType;
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

    private SportType sportType;

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }
}