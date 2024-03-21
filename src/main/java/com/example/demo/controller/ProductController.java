package com.example.demo.controller;


import com.example.demo.model.Product;
import com.example.demo.repository.IProductRepo;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private IProductRepo iProductRepo;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAllCustomer() {
        List<Product> products = (List<Product>) productService.getAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findCustomerById(@PathVariable int id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> saveCustomer(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateCustomer(@PathVariable int id, @RequestBody Product product) {
        Optional<Product> product1 = productService.findById(id);
        if (!product1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setId(product1.get().getId());
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteCustomer(@PathVariable int id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.delete(product.get());
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @GetMapping("/search/{price}")
    public ResponseEntity<List<Product>> searchProduct(@PathVariable double price) {
        List<Product> products = iProductRepo.searchPrice(price);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/asc")
    public ResponseEntity<List<Product>> ASC() {
        List<Product> products = iProductRepo.index();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }



}