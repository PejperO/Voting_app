package org.pejpero.voting_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class Voter {

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Type(type = "uuid-char")
    //@Column(length = 36)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private boolean hasVoted = false;

    //getters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getHasVoted() {
        return hasVoted;
    }

    //setters
    public void setId(UUID id) {
        this.id = id;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    public void setName(String name) {
        this.name = name;
    }
}
