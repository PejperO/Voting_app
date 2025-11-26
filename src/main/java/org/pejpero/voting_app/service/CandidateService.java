package org.pejpero.voting_app.service;

import org.pejpero.voting_app.model.Candidate;
import org.pejpero.voting_app.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);
    }
}
