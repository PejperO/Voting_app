package org.pejpero.voting_app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pejpero.voting_app.model.Candidate;
import org.pejpero.voting_app.model.Voter;
import org.pejpero.voting_app.repository.CandidateRepository;
import org.pejpero.voting_app.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class VotingAppApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private VoterRepository voterRepository;

    @BeforeEach
    void cleanup() {
        candidateRepository.deleteAll();
        voterRepository.deleteAll();
    }

    @Test
    void addCandidateTest() throws Exception {
        Candidate c = new Candidate();
        c.setName("Test Candidate");

        String response = mockMvc.perform(post("/candidates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(c)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JsonNode node = objectMapper.readTree(response);
        assertTrue(node.has("id") && !node.get("id").isNull(), "should have id");
        assertEquals("Test Candidate", node.get("name").asText());
        assertEquals(0, node.get("votesCount").asInt());
    }

    @Test
    void addVoterTest() throws Exception {
        Voter v = new Voter();
        v.setName("Test Voter");

        String response = mockMvc.perform(post("/voters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(v)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JsonNode node = objectMapper.readTree(response);
        assertTrue(node.has("id") && !node.get("id").isNull(), "should have id");
        assertEquals("Test Voter", node.get("name").asText());
        // pole JSON powinno być "hasVoted"
        assertFalse(node.get("hasVoted").asBoolean());
    }

    @Test
    void votingFlowTest() throws Exception {
        // 1) Dodaj kandydata
        String candidateResponse = mockMvc.perform(post("/candidates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Candidate\",\"votesCount\":0}"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String candidateId = objectMapper.readTree(candidateResponse).get("id").asText();

        // 2) Dodaj wyborcę
        String voterResponse = mockMvc.perform(post("/voters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Voter A\"}"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String voterId = objectMapper.readTree(voterResponse).get("id").asText();

        // 3) Oddaj głos
        mockMvc.perform(post("/voters/" + voterId + "/vote/" + candidateId))
                .andExpect(status().isOk());

        // 4) Sprawdź czy votesCount wzrosło
        String candidatesList = mockMvc.perform(get("/candidates"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ArrayNode candArray = (ArrayNode) objectMapper.readTree(candidatesList);
        JsonNode updatedCandidate = findById(candArray, candidateId);
        assertNotNull(updatedCandidate, "candidate should be present after voting");
        assertEquals(1, updatedCandidate.get("votesCount").asInt());

        // 5) Sprawdź czy voter ma hasVoted = true
        String votersList = mockMvc.perform(get("/voters"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ArrayNode voterArray = (ArrayNode) objectMapper.readTree(votersList);
        JsonNode updatedVoter = findById(voterArray, voterId);
        assertNotNull(updatedVoter, "voter should be present after voting");
        assertTrue(updatedVoter.get("hasVoted").asBoolean());
    }

    private JsonNode findById(ArrayNode array, String id) {
        for (JsonNode n : array) {
            if (n.has("id")) {
                // prefer textual comparison (UUIDs), fallback to numeric comparison if JSON id is a number
                if (n.get("id").isTextual() && n.get("id").asText().equals(id)) {
                    return n;
                }
                if (n.get("id").isNumber()) {
                    try {
                        if (n.get("id").asLong() == Long.parseLong(id)) {
                            return n;
                        }
                    } catch (NumberFormatException ex) {
                        // id is not numeric -> ignore
                    }
                }
            }
        }
        return null;
    }
}
