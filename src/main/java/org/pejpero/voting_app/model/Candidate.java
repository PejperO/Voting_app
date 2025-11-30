package org.pejpero.voting_app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private int votesCount = 0;

    @Version
    private Long version;

    public void setNewVote() {
        this.votesCount++;
    }
}
