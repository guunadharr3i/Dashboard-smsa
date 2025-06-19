package com.smsaDashBoard.Smsa.Repo;

import com.smsaDashBoard.Smsa.Entity.SwiftMessageText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// public class SwiftMessageTextRepository {
    
// }
@Repository
public interface SwiftMessageTextRepository extends JpaRepository<SwiftMessageText, Long> {}