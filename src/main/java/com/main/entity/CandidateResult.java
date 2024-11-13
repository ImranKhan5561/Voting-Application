package com.main.entity;

import jakarta.persistence.*;

@Entity
public class CandidateResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    private int voteCount;

    @ManyToOne
    @JoinColumn(name = "election_result_id")
    private ElectionResult electionResult;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public ElectionResult getElectionResult() {
		return electionResult;
	}

	public void setElectionResult(ElectionResult electionResult) {
		this.electionResult = electionResult;
	}

    
}
