package com.example.msproducts.controllers;

import com.example.corelib.exceptions.ResourceNotFoundException;
import com.example.msproducts.entities.Product;
import com.example.msproducts.entities.ProductDto;
import com.example.msproducts.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping()
//    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<ProductDto>> findAll(@RequestParam(value = "minPrice", required = false, defaultValue = "0") Integer minPrice,
                                                    @RequestParam(value = "maxPrice", required = false, defaultValue = "99999") Integer maxPrice) {
        return new ResponseEntity<>(service.findAllByPriceBetween(minPrice, maxPrice), HttpStatus.OK);

    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('ROLE_USER')")
    private ResponseEntity<ProductDto> findById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found")));
    }

    @GetMapping("/{ids}")
    private ResponseEntity<List<ProductDto>> findAllByIds(@PathVariable List<Long> ids) {
        return ResponseEntity.ok(service.findAllByIds(ids));
    }

    @PostMapping
    private ResponseEntity<Product> save(@RequestBody Product product) {
        Product created = service.save(product);
        return ResponseEntity.created(URI.create("/products/" + created.getId())).body(created);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<?> delete(@RequestBody Product product) {
        service.delete(product);
        return ResponseEntity.ok().build();
    }


}

