package com.smsaDashBoard.Smsa.Repo;

import com.smsaDashBoard.Smsa.Entity.SwiftMessageIntervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SwiftInterventionRepository extends JpaRepository<SwiftMessageIntervention, Long> {}
