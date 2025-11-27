package org.pejpero.voting_app.controller;

import org.pejpero.voting_app.model.Candidate;
import org.pejpero.voting_app.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CandidateRepository repo;

    @PostMapping
    public ResponseEntity<Candidate> add(@RequestBody Candidate candidate) {
        Candidate saved = repo.save(candidate);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{candidateId}")
    public ResponseEntity<Void> delete(@PathVariable Long candidateId) {
        repo.deleteById(candidateId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Candidate> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{candidateId}")
    public Candidate getCandidate(@PathVariable Long candidateId) {
        return repo.findById(candidateId).orElseThrow(() -> new RuntimeException("Candidate not found"));
    }

    @GetMapping("/votes/{candidateId}")
    public int getVotesCount(@PathVariable Long candidateId) {
        Candidate candidate = repo.findById(candidateId).orElseThrow(() -> new RuntimeException("Candidate not found"));
        return candidate.getVotesCount();
    }
}

