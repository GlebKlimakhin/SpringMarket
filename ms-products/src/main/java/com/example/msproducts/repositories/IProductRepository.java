package com.example.msproducts.repositories;

import com.example.msproducts.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceBetween(int min, int max);

    List<Product> findAllByTitleContaining(String part);

}
