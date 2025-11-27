const API_URL = "http://localhost:8080";

async function loadData() {
    await loadCandidates();
    await loadVoters();
}

async function loadCandidates() {
    const res = await fetch(`${API_URL}/candidates`);
    const data = await res.json();

    const list = document.getElementById("candidateList");
    const select = document.getElementById("candidateSelect");

    list.innerHTML = "";
    select.innerHTML = "";

    data.forEach(c => {
        list.innerHTML += `<li>${c.name} - Votes: ${c.votesCount}</li>`;
        select.innerHTML += `<option value="${c.id}">${c.name}</option>`;
    });
}

async function loadVoters() {
    const res = await fetch(`${API_URL}/voters`);
    const data = await res.json();

    const list = document.getElementById("voterList");
    const select = document.getElementById("voterSelect");

    list.innerHTML = "";
    select.innerHTML = "";

    data.forEach(v => {
        const status = v.hasVoted ? "(Voted)" : "(Not voted)";
        list.innerHTML += `<li>${v.name} ${status}</li>`;

        if (!v.hasVoted) {
            select.innerHTML += `<option value="${v.id}">${v.name}</option>`;
        }
    });
}

async function addCandidate() {
    const name = document.getElementById("candidateName").value;
    if (!name) return;

    await fetch(`${API_URL}/candidates`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({name})
    });

    document.getElementById("candidateName").value = "";
    loadData();
}

async function addVoter() {
    const name = document.getElementById("voterName").value;
    if (!name) return;

    await fetch(`${API_URL}/voters`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({name})
    });

    document.getElementById("voterName").value = "";
    loadData();
}

async function vote() {
    const voterId = document.getElementById("voterSelect").value;
    const candidateId = document.getElementById("candidateSelect").value;

    if (!voterId || !candidateId) return;

    await fetch(`${API_URL}/voters/${voterId}/vote/${candidateId}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" }
    });

    loadData();
}

loadData();
