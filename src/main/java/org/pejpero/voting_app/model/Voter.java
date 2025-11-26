package org.pejpero.voting_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean hasVoted = false;

    //getters
    public boolean isHasVoted() {
        return hasVoted;
    }

    public String getName() {
        return name;
    }

    //setters
    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    public void setName(String name) {
        this.name = name;
    }
}

