package com.paymentchain.mstransactions.repository;

import com.paymentchain.mstransactions.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query("SELECT t FROM TransactionEntity t WHERE t.ibanAccount = ?1")
    List<TransactionEntity> findByIbanAccount(String ibanAccount);
}