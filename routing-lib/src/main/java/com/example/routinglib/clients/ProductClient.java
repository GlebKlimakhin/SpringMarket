package com.example.routinglib.clients;

import com.example.routinglib.dtos.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("ms-products")
public interface ProductClient {

    @GetMapping("/products/{id}")
    ResponseEntity<ProductDto> findById(Long id);

    @GetMapping("/products/{ids}")
    ResponseEntity<List<ProductDto>> findByIds(List<Long> ids);

    @GetMapping("products/")
    ResponseEntity<List<ProductDto>> findAll(Integer minPrice, Integer maxPrice);



}
