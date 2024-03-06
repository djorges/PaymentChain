package com.paymentchain.msproduct.controller;

import com.paymentchain.msproduct.entity.ProductEntity;
import com.paymentchain.msproduct.service.IProductService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping("/")
    public ResponseEntity<List<ProductEntity>> listAll(){
        val list = service.listAll();
        if(list == null || list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ProductEntity> save(
            @RequestBody ProductEntity entity
    ){
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getById(
        @PathVariable Long id
    ){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> update(
        @PathVariable Long id,
        @RequestBody ProductEntity entity
    ){
        return new ResponseEntity<>(service.update(id, entity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
        @PathVariable Long id
    ){
        service.delete(id);

        return new ResponseEntity<>("Product deleted", HttpStatus.OK);
    }
}
