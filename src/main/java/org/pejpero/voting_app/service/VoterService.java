package org.pejpero.voting_app.service;

import org.pejpero.voting_app.model.Voter;
import org.pejpero.voting_app.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VoterService {

    @Autowired
    private VoterRepository voterRepository;

    public Voter add(Voter voter) {
        return voterRepository.save(voter);
    }

    public void deleteById(UUID voterId) {
        voterRepository.deleteById(voterId);
    }

    public List<Voter> findAll() {
        return voterRepository.findAll();
    }

    public Voter getVoter(UUID voterId) {
        return voterRepository.findById(voterId).orElse(null);
    }
}
