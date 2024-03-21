package com.example.demo.service;


import com.example.demo.model.Product;
import com.example.demo.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepo iProductRepo;
    @Override
    public List<Product> getAll() {
        return iProductRepo.findAll();
    }

    @Override
    public Product save(Product product) {
        return iProductRepo.save(product);
    }

    @Override
    public Optional<Product> findById(int id) {
        return iProductRepo.findById(id);
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Product product) {
        iProductRepo.delete(product);
    }
}