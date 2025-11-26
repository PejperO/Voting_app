package org.pejpero.voting_app.controller;

import org.pejpero.voting_app.model.Candidate;
import org.pejpero.voting_app.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CandidateRepository repo;

    @PostMapping
    public Candidate add(@RequestBody Candidate c) {
        return repo.save(c);
    }

    @GetMapping
    public List<Candidate> getAll() {
        return repo.findAll();
    }
}

