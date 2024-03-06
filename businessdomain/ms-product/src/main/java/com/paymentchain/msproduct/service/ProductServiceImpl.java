package com.paymentchain.msproduct.service;

import com.paymentchain.msproduct.entity.ProductEntity;
import com.paymentchain.msproduct.exception.ProductNotFoundException;
import com.paymentchain.msproduct.repository.IProductRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class ProductServiceImpl implements IProductService{
    @Autowired
    private IProductRepository repository;

    @Override
    public List<ProductEntity> listAll() {
        return repository.findAll();
    }

    @Override
    public ProductEntity save(ProductEntity entity) {
        return repository.save(entity);
    }

    @Override
    public ProductEntity getById(Long id){
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public ProductEntity update(Long id, ProductEntity entity){
        val product = getById(id);

        product.setName(entity.getName());
        product.setCode(entity.getCode());

        repository.save(product);

        return product;
    }

    @Override
    public void delete(Long id){
        getById(id);

        repository.deleteById(id);
    }
}
