package org.pejpero.voting_app.service;

import org.pejpero.voting_app.model.Candidate;
import org.pejpero.voting_app.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public Candidate add(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public void deleteById(UUID candidateId) {
        candidateRepository.deleteById(candidateId);
    }

    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    public Candidate getCandidate(UUID candidateId) {
        return candidateRepository.findById(candidateId).orElse(null);
    }

    public int getVotesCount(UUID candidateId) {
        Optional<Candidate> candidate = candidateRepository.findById(candidateId);
        return candidate.map(Candidate::getVotesCount).orElse(0); // not null bc NullPointerException
    }
}
