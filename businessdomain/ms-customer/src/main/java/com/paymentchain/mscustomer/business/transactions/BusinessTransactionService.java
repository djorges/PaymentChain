package com.paymentchain.mscustomer.business.transactions;

import com.fasterxml.jackson.databind.JsonNode;
import com.paymentchain.mscustomer.entity.CustomerEntity;
import com.paymentchain.mscustomer.repository.ICustomerRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class BusinessTransactionService {
    @Autowired
    private ICustomerRepository repository;

    @Autowired
    @Qualifier("productWebClient")
    private WebClient productWebClient;

    @Autowired
    @Qualifier("transactionWebClient")
    private WebClient transactionWebClient;

    public CustomerEntity getByCode(
            @RequestParam String code
    ){
        CustomerEntity customer = repository.findByCode(code);

        //find all product names that belong to this customer
        val productEntities = customer.getProducts();
        productEntities.forEach(x->{
            //Get product name from ms-product
            String productName = getProductName(x.getId());
            x.setProductName(productName);
        });

        //find all transactions that belong to this customer account number
        val transactions = getTransactions(customer.getIban());
        customer.setTransactions(transactions);

        return customer;
    }

    /**
     * Call ms-product to find a product by id and return its name
     * @param id product id
     * @return name of product if it was found
     * */
    private String getProductName(long id){
        val obj = productWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/product/{id}")
                        .build(id)
                )
                .retrieve()
                .bodyToMono(JsonNode.class);

        return obj.block()
                .get("name")
                .asText();
    }

    /**
     * Call ms-transaction to find all transactions that belong to the given account
     * @param iban account number of the customer
     * @return list of transactions
     * */
    private List<?> getTransactions(String iban){
        val obj = transactionWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/transaction/customer/transactions")
                        .queryParam("ibanAccount", iban)
                        .build()
                ).retrieve().bodyToFlux(Object.class).collectList().block();

        return obj;

    }
}
