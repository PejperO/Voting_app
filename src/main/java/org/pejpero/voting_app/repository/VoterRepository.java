package org.pejpero.voting_app.repository;

import org.pejpero.voting_app.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {

}
