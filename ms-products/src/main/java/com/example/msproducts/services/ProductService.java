package com.example.msproducts.services;

import com.example.corelib.exceptions.ResourceNotFoundException;
import com.example.msproducts.entities.Product;
import com.example.msproducts.entities.ProductDto;
import com.example.msproducts.repositories.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository repository;

    private final ModelMapper modelMapper;

    public Optional<ProductDto> findById(Long id) {
        return repository.findById(id).map(this::toDto);
    }

    public Product save(Product product){
       return repository.save(product);
    }

    public void delete(Product product){
        repository.delete(product);
    }

    public void deleteById(String id){
        repository.deleteById(Long.parseLong(id));
    }

    public List<ProductDto> findByTitleContaining(String part){
        return repository.findAllByTitleContaining(part).stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<ProductDto> findAllByPriceBetween(Integer min, Integer max){
        return repository.findAllByPriceBetween(min, max).stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<ProductDto> findAllByIds(List<Long> ids) {
        List<Product> products = repository.findAll();
        List<ProductDto> outputList = new ArrayList<>();
        for(int i = 0; i <= products.size(); i++) {
            for(int j = 0; j <= ids.size(); j++) {
                if(products.get(i).getId() == ids.get(j)) {
                    outputList.add(toDto(products.get(i)));
                }
            }
        }
        if(outputList.isEmpty()) {
            throw new ResourceNotFoundException("No products with such ids");
        }
        return outputList;
    }

    private ProductDto toDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    private Product toProduct(ProductDto dto) {
        return modelMapper.map(dto, Product.class);
    }



}
