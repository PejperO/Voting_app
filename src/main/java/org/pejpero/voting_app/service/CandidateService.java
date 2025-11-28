package org.pejpero.voting_app.service;

import org.pejpero.voting_app.model.Candidate;
import org.pejpero.voting_app.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

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
        return candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
    }

    public int getVotesCount(UUID candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        return candidate.getVotesCount();
    }
}
