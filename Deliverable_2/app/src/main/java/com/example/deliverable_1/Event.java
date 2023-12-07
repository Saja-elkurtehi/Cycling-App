package com.example.deliverable_1;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private String eventName;
    private String eventRoute;
    private String eventDifficulty;
    private String participantLimit;

    List<Participant> participantList;

    // Empty constructor needed for Firebase
    public Event() {
    }

    public Event(String eventName, String eventRoute, String eventDifficulty, String participantLimit) {
        this.eventName = eventName;
        this.eventRoute = eventRoute;
        this.eventDifficulty = eventDifficulty;
        this.participantLimit = participantLimit;
        this.participantList = new ArrayList<>();
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventRoute() {
        return eventRoute;
    }

    public void setEventRoute(String eventRoute) {
        this.eventRoute = eventRoute;
    }

    public String getEventDifficulty() {
        return eventDifficulty;
    }

    public void setEventDifficulty(String eventDifficulty) {
        this.eventDifficulty = eventDifficulty;
    }

    public String getParticipantLimit() {
        return participantLimit;
    }

    public void setParticipantLimit(String participantLimit) {
        this.participantLimit = participantLimit;
    }

    public String toString(){
        return eventName;
    }

    public void addParticipant(Participant participant){
        participantList.add(participant);
    }
}
