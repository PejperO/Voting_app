package org.pejpero.voting_app;

import org.pejpero.voting_app.model.Candidate;
import org.pejpero.voting_app.model.Voter;
import org.pejpero.voting_app.repository.CandidateRepository;
import org.pejpero.voting_app.repository.VoterRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class VotingAppApplication implements CommandLineRunner {

    private final VoterRepository repo;
    private final CandidateRepository cRepo;

    public VotingAppApplication(VoterRepository repo, CandidateRepository cRepo) {
        this.repo = repo;
        this.cRepo = cRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(VotingAppApplication.class, args);
    }

    @Override
    public void run(String... args){
        if(repo.count()==0 && cRepo.count()==0) {
            for (int i = 1; i <= 10; i++) {
                Voter voter = new Voter();
                voter.setName("Voter " + i);
                repo.save(voter);
            }

            Candidate candidate = new Candidate();
            candidate.setName("President A");
            cRepo.save(candidate);

            Candidate candidate2 = new Candidate();
            candidate2.setName("President B");
            cRepo.save(candidate2);
        }
    }
}
