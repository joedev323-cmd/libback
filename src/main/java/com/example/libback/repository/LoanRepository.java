package com.example.libback.repository;

import com.example.libback.model.Loan;
import com.example.libback.model.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    // Maps the explicitly defined field path (l.borrower.borrowerId)
    @Query("SELECT l FROM Loan l WHERE l.borrower.borrowerId = :borrowerId")
    List<Loan> findLoansByBorrowerId(@Param("borrowerId") String borrowerId);

    // Correct standard Spring DSL nesting strategy to target Borrower -> borrowerId
    long countByBorrowerBorrowerIdAndStatus(String borrowerId, LoanStatus status);
    
    long countByStatus(LoanStatus status);

    long countByStatusAndDueDateBefore(LoanStatus status, LocalDateTime dateTime);

    List<Loan> findTop5ByOrderByLoanIdDesc();
    
    // Finds the active loan tracking record for a specific book copy
    Optional<Loan> findByAccessionAccessionIdAndStatus(String accessionId, LoanStatus status);

    /* * COMMENTED OUT TO PRESERVE APPLICATION CONTEXT UNTIL 'category' IS ADDED TO ITEM ENTITY
     * * @Query("SELECT l.accession.item.category, COUNT(l) FROM Loan l " +
     * "WHERE l.status = com.example.libback.model.enums.LoanStatus.ACTIVE " +
     * "GROUP BY l.accession.item.category")
     * List<Object[]> countActiveLoansByCategory();
     */
    // Inside com.example.libback.repository.LoanRepository.java

    @Query("SELECT COALESCE(SUM(l.finePaid), 0) FROM Loan l")
    BigDecimal sumTotalFinesCollected();
}