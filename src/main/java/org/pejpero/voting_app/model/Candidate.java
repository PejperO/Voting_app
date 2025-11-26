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
    public void setVotesCount(int votesCount) {
        this.votesCount = votesCount;
    }

    public String getName() {
        return name;
    }

    //setters
    public int getVotesCount() {
        return votesCount;
    }

    public void setNewVote() {
        this.votesCount++;
    }

    public void setName(String name) {
        this.name = name;
    }

}

