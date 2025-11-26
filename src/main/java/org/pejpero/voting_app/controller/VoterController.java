package org.pejpero.voting_app.controller;

import org.pejpero.voting_app.model.Voter;
import org.pejpero.voting_app.repository.VoterRepository;
import org.pejpero.voting_app.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voters")
public class VoterController {

    @Autowired
    private VoterRepository repo;

    @Autowired
    private VotingService votingService;

    @PostMapping
    public Voter add(@RequestBody Voter v) {
        return repo.save(v);
    }

    @GetMapping
    public List<Voter> getAll() {
        return repo.findAll();
    }

}

