package com.smsaDashBoard.Smsa.Repo;

import com.smsaDashBoard.Smsa.Entity.SwiftMessageHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.repository.query.Param;

@Repository
public interface SwiftMessageHeaderRepository extends JpaRepository<SwiftMessageHeader, Long> {

    // 1. Count total records
    @Query("SELECT COUNT(s) FROM SwiftMessageHeader s")
    long countAllRecords();

    // 2. Count non-null inputRef
    @Query("SELECT COUNT(s) FROM SwiftMessageHeader s WHERE s.inputRefNo IS NOT NULL")
    long countInputRefs();

    // 3. Count non-null outputRef
    @Query("SELECT COUNT(s) FROM SwiftMessageHeader s WHERE s.outputRefNo IS NOT NULL")
    long countOutputRefs();

    // 4. Distinct messageType with counts
    @Query("SELECT s.msgType, COUNT(s) FROM SwiftMessageHeader s GROUP BY s.msgType")
    List<Object[]> countByMessageType();

    @Query("SELECT s FROM SwiftMessageHeader s WHERE s.transactionRef IN :txnRefs")
    List<SwiftMessageHeader> findByTransactionRefIn(@Param("txnRefs") List<String> txnRefs);
}
