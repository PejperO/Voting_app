# Voting_app

A full-stack voting application built with **Java ([Spring Boot](https://spring.io/projects/spring-boot))** for the backend and **JavaScript** for the frontend. 
The application allows users to manage candidates, voters, and votes in a simple and intuitive way. 
It provides a RESTful API for backend operations and a dynamic frontend for user interaction.

This project is ideal for learning how to build a CRUD application with a focus on REST APIs, database integration, and frontend-backend communication.

---

## Table of Contents

- [Project Overview](#project-overview)
- [ Features](#-features)
- [ Project Structure](#-project-structure)
- [ Database](#️-database)
- [ API Endpoints](#-api-endpoints)
- [ User Interface](#-user-interface)
- [ How to Run](#-how-to-run)
- [ Screenshots](#️-screenshots)
- [ License](#-license)
- [ Author](#️-author)

---

## Project Overview

This application provides the following functionalities:
- **Candidate Management**: Add, view, and delete candidates. Each candidate has a name and a vote count.
- **Voter Management**: Add, view, and filter voters based on their voting status.
- **Voting System**: Allow voters to cast votes for candidates. Each voter can vote only once.
- **Dynamic Frontend**: A user-friendly interface built with HTML and JavaScript for interacting with the system.

The backend is powered by **Spring Boot**, using **H2 Database** for data storage. The frontend communicates with the backend via RESTful APIs.

---

##  Features

- Add new candidates and voters.
- Cast votes for candidates.
- View the list of candidates with their vote counts.
- Filter voters by those who have voted or not voted.
- RESTful API for managing candidates and voters.
- Dynamic frontend with real-time updates.

---

##  Project Structure

```
Voting_app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── org.pejpero.voting_app/
│   │   │   │   ├── controller/       # REST controllers
│   │   │   │   ├── model/            # JPA entities
│   │   │   │   ├── repository/       # Spring Data JPA repositories
│   │   │   │   ├── service/          # Business logic
│   │   ├── resources/
│   │   │   ├── static/               # Frontend files (HTML, CSS, JS)
│   │   │   ├── application.properties
├── README.md                         # Project documentation
```

---

##  Database

The application uses an embedded H2 database for simplicity. The database contains two main tables:

1. **Candidates**:
   - `id`: Unique identifier.
   - `name`: Name of the candidate.
   - `votesCount`: Number of votes received.

2. **Voters**:
   - `id`: Unique identifier.
   - `name`: Name of the voter.
   - `hasVoted`: Boolean indicating if the voter has voted.

---

##  API Endpoints

### CandidateController

- **POST `/candidates`**: Add a new candidate.
- **GET `/candidates`**: Retrieve all candidates.
- **GET `/candidates/{candidateId}`**: Retrieve a specific candidate.
- **GET `/candidates/votes/{candidateId}`**: Get the vote count for a specific candidate.
- **DELETE `/candidates/{candidateId}`**: Delete a candidate.

### Example Java Code: CandidateController

```java
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

    @GetMapping("/{candidateId}")
    public Candidate getCandidate(@PathVariable Long candidateId) {
        return repo.findById(candidateId).orElseThrow(() -> new RuntimeException("Candidate not found"));
    }

    @DeleteMapping("/{candidateId}")
    public ResponseEntity<Void> delete(@PathVariable Long candidateId) {
        repo.deleteById(candidateId);
        return ResponseEntity.noContent().build();
    }
}
```

### VoterController

- **POST `/voters`**: Add a new voter.
- **GET `/voters`**: Retrieve all voters.
- **GET `/voters/{voterId}`**: Retrieve a specific voter.
- **POST `/voters/{voterId}/vote/{candidateId}`**: Cast a vote for a candidate.

### Example API Usage

#### Add a Candidate
```javascript
fetch("http://localhost:8080/candidates", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ name: "John Doe" })
}).then(response => response.json())
  .then(data => console.log(data));
```

#### Get All Candidates
```javascript
fetch("http://localhost:8080/candidates")
    .then(response => response.json())
    .then(data => console.log(data));
```

---

##  User Interface

The frontend is a simple HTML page with JavaScript for dynamic updates. Key features include:
- Dropdowns for selecting candidates and voters.
- Buttons for adding new candidates and voters.
- Lists displaying candidates and voters with their statuses.

### Example Frontend Code
```javascript
async function loadCandidates() {
    const res = await fetch("http://localhost:8080/candidates");
    const data = await res.json();
    console.log(data);
}
```

---

##  How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/PejperO/Voting_app.git
   cd Voting_app
   ```

2. Build the project using Maven:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Open the application in your browser:
   ```
   http://localhost:8080
   ```

---

##  Screenshots

<img width="988" height="1284" alt="{F4F71799-71A2-48AE-A8B2-A8BC7BD8AC36}" src="https://github.com/user-attachments/assets/77d251d0-7609-4334-825c-339dd1e7c65b" />
<img width="795" height="968" alt="{3909B94E-89F3-4BBF-A763-2CE9E4C440BC}" src="https://github.com/user-attachments/assets/300c3e86-26d2-4712-a3af-58bdc8e1aec0" />

---

##  License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

##  Author

**[PejperO](https://pejpero.com)**

---
