package org.pejpero.voting_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int votesCount = 0;

    //getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVotesCount() {
        return votesCount;
    }

    //setters
    public void setId(Long id) {
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
