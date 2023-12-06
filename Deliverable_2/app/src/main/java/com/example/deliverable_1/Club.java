package com.example.deliverable_1;

import java.util.ArrayList;
import java.util.List;

public class Club {

    List<Participant> participantList;
    List<Event> events;

    public Club(){
        this.participantList = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public List<Participant> getParticipantList() {
        return this.participantList;
    }

    public List<Event> getEvents() {
        return this.events;
    }
}
