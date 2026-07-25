package com.example.Ecommerce.Service;

import com.example.Ecommerce.Dto.ProductDto.ProductRequestDto;
import com.example.Ecommerce.Dto.ProductDto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto save(ProductRequestDto dto);

    ProductResponseDto getById(Long id);

    List<ProductResponseDto> getAll();

    ProductResponseDto update(Long id, ProductRequestDto dto);

    void delete(Long id);

}
