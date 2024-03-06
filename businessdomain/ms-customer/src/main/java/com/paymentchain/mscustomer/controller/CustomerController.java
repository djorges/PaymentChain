package com.paymentchain.mscustomer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.paymentchain.mscustomer.business.transactions.BusinessTransactionService;
import com.paymentchain.mscustomer.entity.CustomerEntity;
import com.paymentchain.mscustomer.entity.ProductEntity;
import com.paymentchain.mscustomer.exception.CustomerNotFoundException;
import com.paymentchain.mscustomer.repository.ICustomerRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private ICustomerRepository repository;

    @Autowired
    private BusinessTransactionService transactionService;

    @GetMapping("/")
    public ResponseEntity<List<CustomerEntity>> listAll(){
       return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CustomerEntity> save(
        @RequestBody CustomerEntity entity
    ){
        entity.getProducts().forEach(x->x.setCustomer(entity)); //
        return new ResponseEntity<>(repository.save(entity), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> getById(
        @PathVariable Long id
    ){
        val customer = repository.findById(id).orElseThrow(
                ()->new CustomerNotFoundException("Customer does not exists")
        );
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/full")
    public CustomerEntity getByCode(
            @RequestParam String code
    ){
        val customer = repository.findByCode(code);


        return customer;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerEntity> update(
            @PathVariable Long id,
            @RequestBody CustomerEntity customer
    ){
        val entity =  repository.findById(id).orElseThrow(
            ()->new CustomerNotFoundException("Customer does not exists")
        );

        entity.setName(customer.getName());
        entity.setPhone(customer.getPhone());

        repository.save(entity);

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){

        repository.findById(id).orElseThrow(
                ()->new CustomerNotFoundException("Customer does not exists")
        );

        repository.deleteById(id);

        return new ResponseEntity<>("Product deleted", HttpStatus.OK);
    }
}
