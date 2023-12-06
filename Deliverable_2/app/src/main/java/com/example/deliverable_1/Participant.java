package com.example.deliverable_1;

import java.util.ArrayList;
import java.util.List;

public class Participant extends User{

    List<Event> eventsJoined;

    public Participant() {
        super();
        eventsJoined = new ArrayList<>();
    }

}
