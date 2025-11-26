package org.pejpero.voting_app.controller;

import org.pejpero.voting_app.model.Candidate;
import org.pejpero.voting_app.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.pejpero.voting_app.service.CandidateService;

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

    @GetMapping
    public List<Candidate> getAll() {
        return repo.findAll();
    }
}

