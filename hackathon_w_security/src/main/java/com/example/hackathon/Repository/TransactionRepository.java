package com.example.hackathon.Repository;

import com.example.hackathon.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.myUser.id = :userId")

    Double findTotalExpensesByUserId(@Param("userId") Long userId);


    List<Transaction> findAllByMyUserId(Long userId);
}