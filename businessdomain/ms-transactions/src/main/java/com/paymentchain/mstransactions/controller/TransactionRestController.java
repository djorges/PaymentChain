package com.paymentchain.mstransactions.controller;

import com.paymentchain.mstransactions.entity.TransactionEntity;
import com.paymentchain.mstransactions.repository.ITransactionRepository;
import com.paymentchain.mstransactions.exception.TransactionNotFoundException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transaction")
public class TransactionRestController {

    @Autowired
    private ITransactionRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<TransactionEntity>> listAll(){
        val list = repository.findAll();
        if(list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionEntity> getById(
        @PathVariable Long id
    ){
        val transaction = repository.findById(id).orElseThrow(
            ()-> new TransactionNotFoundException("Customer does not exists")
        );
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> put(
        @PathVariable long id,
        @RequestBody TransactionEntity input
    ){
        val transaction = repository.findById(id).orElseThrow(
            () -> new TransactionNotFoundException("Customer does not exists")
        );

        if(transaction != null){
            transaction.setAmount(input.getAmount());
            transaction.setChannel(input.getChannel());
            transaction.setDate(input.getDate());
            transaction.setDescription(input.getDescription());
            transaction.setFee(input.getFee());
            transaction.setIbanAccount(input.getIbanAccount());
            transaction.setReference(input.getReference());
            transaction.setStatus(input.getStatus());

            repository.save(transaction);
        }

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> post(
        @RequestBody TransactionEntity input
    ) {
        return new ResponseEntity<>(repository.save(input), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable long id
    ) {
        val transaction = repository.findById(id).orElseThrow(
            () -> new TransactionNotFoundException("Customer does not exists")
        );

        repository.delete(transaction);

        return new ResponseEntity<>("Transaction deleted", HttpStatus.OK);
    }

    @GetMapping("/customer/transactions")
    public ResponseEntity<List<TransactionEntity>> getByIbanAccount(
            @RequestParam String ibanAccount
    ) {
        val list = repository.findByIbanAccount(ibanAccount);
        if(list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}