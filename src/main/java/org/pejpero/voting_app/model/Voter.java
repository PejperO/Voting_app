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
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getHasVoted() {
        return hasVoted;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    public void setName(String name) {
        this.name = name;
    }
}
