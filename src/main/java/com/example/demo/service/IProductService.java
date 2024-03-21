package com.example.demo.service;


import com.example.demo.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<Product> getAll();
    Product save(Product product);

    Optional<Product> findById(int id);

    void update(Product product);
    void delete(Product product);
}
