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
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private boolean hasVoted = false;

    @Version
    private Long version;
}
