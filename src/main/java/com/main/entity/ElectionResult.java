package com.main.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ElectionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election election;

    @OneToMany(mappedBy = "electionResult", cascade = CascadeType.ALL)
    private List<CandidateResult> candidateResults;

    private LocalDateTime resultDeclaredDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}

	public List<CandidateResult> getCandidateResults() {
		return candidateResults;
	}

	public void setCandidateResults(List<CandidateResult> candidateResults) {
		this.candidateResults = candidateResults;
	}

	public LocalDateTime getResultDeclaredDate() {
		return resultDeclaredDate;
	}

	public void setResultDeclaredDate(LocalDateTime resultDeclaredDate) {
		this.resultDeclaredDate = resultDeclaredDate;
	}

    
}
