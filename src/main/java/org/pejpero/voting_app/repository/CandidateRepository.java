package org.pejpero.voting_app.repository;
import org.pejpero.voting_app.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

}