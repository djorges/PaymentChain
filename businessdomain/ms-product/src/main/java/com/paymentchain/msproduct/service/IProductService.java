package com.paymentchain.msproduct.service;

import com.paymentchain.msproduct.entity.ProductEntity;

import java.util.List;

public interface IProductService {
    List<ProductEntity> listAll();
    ProductEntity save(ProductEntity entity);

    ProductEntity getById(Long id);

    ProductEntity update(Long id, ProductEntity entity);

    void delete(Long id);
}
