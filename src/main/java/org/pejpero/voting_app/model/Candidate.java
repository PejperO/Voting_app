package org.pejpero.voting_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class Candidate {

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Type(type = "uuid-char")
    //@Column(length = 36)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private int votesCount = 0;

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
