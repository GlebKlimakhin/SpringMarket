package com.example.msproducts.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductDto {

    private long id;
    private String title;
    private int price;

}
