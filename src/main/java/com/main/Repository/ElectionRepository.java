package com.main.Repository;



import com.main.entity.Election;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionRepository extends JpaRepository<Election, Long> {
	List<Election> findByResultDeclaredFalse();
	List<Election> findByResultDeclared(boolean resultDeclared);
}
