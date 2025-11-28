package org.pejpero.voting_app.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private boolean hasVoted = false;

    @SuppressWarnings("unused") // Used by JPA
    @Version
    private Long version;

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
