package org.pejpero.voting_app.controller;

import org.pejpero.voting_app.model.Voter;
import org.pejpero.voting_app.service.VoterService;
import org.pejpero.voting_app.service.VotingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/voters")
public class VoterController {

    private final VoterService repo;
    private final VotingService votingService;

    public VoterController(VoterService repo, VotingService votingService) {
        this.repo = repo;
        this.votingService = votingService;
    }
    @PostMapping
    public ResponseEntity<Voter> add(@RequestBody Voter voter) {
        Voter saved = repo.add(voter);
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
        return repo.getVoter(voterId);
    }

    @PostMapping("/{voterId}/vote/{candidateId}")
    public boolean vote(@PathVariable UUID voterId, @PathVariable UUID candidateId) {
        return votingService.castVote(candidateId, voterId);
    }
}
