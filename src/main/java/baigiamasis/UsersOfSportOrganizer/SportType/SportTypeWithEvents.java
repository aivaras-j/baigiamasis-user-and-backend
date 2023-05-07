package baigiamasis.UsersOfSportOrganizer.SportType;

import baigiamasis.UsersOfSportOrganizer.Event.Event;

import java.util.List;

public class SportTypeWithEvents {

    private SportType sportType;

    private List<Event> events;

    public SportTypeWithEvents() {
    }

    public SportTypeWithEvents(SportType sportType, List<Event> events) {
        this.sportType = sportType;
        this.events = events;
    }

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
