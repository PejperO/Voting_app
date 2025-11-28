package org.pejpero.voting_app.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private int votesCount = 0;

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

    public int getVotesCount() {
        return votesCount;
    }

    //setters
    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVotesCount(int votesCount) {
        this.votesCount = votesCount;
    }

    // helper
    public void setNewVote() {
        this.votesCount++;
    }
}
