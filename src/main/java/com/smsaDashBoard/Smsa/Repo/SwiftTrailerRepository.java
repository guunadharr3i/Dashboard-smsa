package com.smsaDashBoard.Smsa.Repo;

import com.smsaDashBoard.Smsa.Entity.SwiftMessageTrailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwiftTrailerRepository extends JpaRepository<SwiftMessageTrailer,Long >{
    
}
