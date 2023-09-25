package com.ooothla.productservice.services;

import com.ooothla.productservice.dtos.GenericProductDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(Long id);
    List<GenericProductDto> getAllProducts();
    GenericProductDto createProduct(GenericProductDto genericProductDto);
    GenericProductDto deleteProduct(Long id);

    GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto);
}
