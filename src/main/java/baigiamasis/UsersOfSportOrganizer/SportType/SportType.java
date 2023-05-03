package baigiamasis.UsersOfSportOrganizer.SportType;

import baigiamasis.UsersOfSportOrganizer.Event.Event;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class SportType {

    @NonNull
    private int id;


    @NonNull

    private String name;

    private List<Event> events = new ArrayList<>();

    public List<Event> getEvents() {
        return events;
    }



}