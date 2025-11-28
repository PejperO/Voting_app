package org.pejpero.voting_app.controller;

import org.pejpero.voting_app.model.Voter;
import org.pejpero.voting_app.repository.VoterRepository;
import org.pejpero.voting_app.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/voters")
public class VoterController {

    @Autowired
    private VoterRepository repo;

    @Autowired
    private VotingService votingService;

    @PostMapping
    public ResponseEntity<Voter> add(@RequestBody Voter voter) {
        Voter saved = repo.save(voter);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{voterId}")
    public ResponseEntity<Void> delete(@PathVariable UUID voterId) {
        repo.deleteById(voterId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Voter> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{voterId}")
    public Voter getVoter(@PathVariable UUID voterId){
        return repo.findById(voterId).orElseThrow(() -> new RuntimeException("Voter not found"));
    }

    @PostMapping("/{voterId}/vote/{candidateId}")
    public boolean vote(@PathVariable UUID voterId, @PathVariable UUID candidateId) {
        votingService.castVote(candidateId, voterId);
        return true;
    }
}

