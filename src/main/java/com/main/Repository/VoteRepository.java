package com.main.Repository;




import com.main.entity.Vote;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends JpaRepository<Vote, Long> {
	 boolean existsByVoterCardNoAndElectionId(String voterCardNo, Long electionId);
	 @Query("SELECT v.candidateId, COUNT(v) FROM Vote v WHERE v.election.id = :electionId GROUP BY v.candidateId")
	 List<Object[]> countVotesByElection(@Param("electionId") Long electionId);


	@Query("SELECT c.party, c.name, COUNT(v), e.name " +
			"FROM Vote v " +
			"JOIN v.election e " +
			"JOIN Candidate c ON v.candidateId = c.id " +
			"WHERE e.id = :electionId " +
			"GROUP BY c.party, c.name, e.name " +
			"ORDER BY COUNT(v) DESC")
	List<Object[]> getLiveResultsByElection(@Param("electionId") Long electionId);

   
	@Query("SELECT e.name, c.name, v.voteDate, " +
		       "CASE WHEN e.resultDeclared = false THEN 'Undeclared' " +
		       "WHEN cr.voteCount = (SELECT MAX(cr2.voteCount) FROM CandidateResult cr2 WHERE cr2.electionResult = cr.electionResult) " +
		       "THEN 'Won' ELSE 'Lost' END " +
		       "FROM Vote v " +
		       "JOIN v.election e " +
		       "JOIN Candidate c ON v.candidateId = c.id " +
		       "LEFT JOIN CandidateResult cr ON cr.candidate = c AND cr.electionResult.election = e " +
		       "WHERE v.voterCardNo = :voterCardNo")
		List<Object[]> getVotingHistoryByVoter(@Param("voterCardNo") String voterCardNo);



}
