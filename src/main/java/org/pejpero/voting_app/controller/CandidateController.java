package org.pejpero.voting_app.controller;

import org.pejpero.voting_app.model.Candidate;
import org.pejpero.voting_app.service.CandidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService repo;

    public CandidateController(CandidateService repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<Candidate> add(@RequestBody Candidate candidate) {
        Candidate saved = repo.add(candidate);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{candidateId}")
    public ResponseEntity<Void> delete(@PathVariable UUID candidateId) {
        repo.deleteById(candidateId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Candidate> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{candidateId}")
    public Candidate getCandidate(@PathVariable UUID candidateId) {
        return repo.getCandidate(candidateId);
    }

    @GetMapping("/votes/{candidateId}")
    public int getVotesCount(@PathVariable UUID candidateId) {
        return repo.getVotesCount(candidateId);
    }
}
