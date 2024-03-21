package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepo extends JpaRepository<Product,Integer> {
    @Query("select p from  Product  p where  p.price = ?1")
    List<Product> searchPrice(double price);

    @Query("select p from Product p order by p.price asc ")
    List<Product> index();

}
