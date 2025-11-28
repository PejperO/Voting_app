package org.pejpero.voting_app.service;

import org.pejpero.voting_app.model.Candidate;
import org.pejpero.voting_app.model.Voter;
import org.pejpero.voting_app.repository.CandidateRepository;
import org.pejpero.voting_app.repository.VoterRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VotingService {

    private final CandidateRepository candidateRepository;
    private final VoterRepository voterRepository;

    public VotingService(
            CandidateRepository candidateRepository,
            VoterRepository voterRepository
    ) {
        this.candidateRepository = candidateRepository;
        this.voterRepository = voterRepository;
    }

    public boolean castVote(UUID candidateId, UUID voterId) {
        Voter voter = voterRepository.findById(voterId)
                .orElseThrow(() -> new RuntimeException("Voter not found"));

        if (voter.getHasVoted()) {
            throw new RuntimeException("Voter has already voted");
        }

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        candidate.setNewVote();
        voter.setHasVoted(true);

        candidateRepository.save(candidate);
        voterRepository.save(voter);
        return true;
    }
}
