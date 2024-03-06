package com.paymentchain.mscustomer.repository;

import com.paymentchain.mscustomer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity,Long> {

    @Query("SELECT c FROM CustomerEntity c WHERE c.code = ?1")
    public CustomerEntity findByCode(String code);

    @Query("SELECT c FROM CustomerEntity c WHERE c.iban = ?1")
    public CustomerEntity findByAccount(String iban);

}
