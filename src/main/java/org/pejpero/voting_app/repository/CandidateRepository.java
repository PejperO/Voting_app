package org.pejpero.voting_app.repository;
import org.pejpero.voting_app.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CandidateRepository extends JpaRepository<Candidate, UUID> {

}