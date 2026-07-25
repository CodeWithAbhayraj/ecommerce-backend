package com.example.Ecommerce.ServiceImpl;

import com.example.Ecommerce.Dto.ProductDto.ProductRequestDto;
import com.example.Ecommerce.Dto.ProductDto.ProductResponseDto;
import com.example.Ecommerce.Exception.ResourceNotFoundException;
import com.example.Ecommerce.Model.Product;
import com.example.Ecommerce.Repository.ProductRepository;
import com.example.Ecommerce.Service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public ProductResponseDto save(ProductRequestDto dto) {

        Product product = modelMapper.map(dto, Product.class);

        Product savedProduct = productRepository.save(product);

        ProductResponseDto response = modelMapper.map(savedProduct, ProductResponseDto.class);

        if(savedProduct.getSeller()!=null){
            response.setSellername(savedProduct.getSeller().getName());
        }

        return response;
    }



    @Override
    public ProductResponseDto getById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));

        ProductResponseDto response = modelMapper.map(product, ProductResponseDto.class);

        if(product.getSeller()!=null){
            response.setSellername(product.getSeller().getName());
        }

        return response;
    }

    @Override
    public List<ProductResponseDto> getAll() {

        return productRepository.findAll()
                .stream()
                .map(product -> {

                    ProductResponseDto dto = modelMapper.map(product, ProductResponseDto.class);

                    if(product.getSeller()!=null){
                        dto.setSellername(product.getSeller().getName());
                    }

                    return dto;

                }).collect(Collectors.toList());

    }

    @Override
    public ProductResponseDto update(Long id, ProductRequestDto dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));

        modelMapper.map(dto, product);

        Product updatedProduct = productRepository.save(product);

        ProductResponseDto response = modelMapper.map(updatedProduct, ProductResponseDto.class);

        if(updatedProduct.getSeller()!=null){
            response.setSellername(updatedProduct.getSeller().getName());
        }

        return response;
    }

    @Override
    public void delete(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));

        productRepository.delete(product);
    }

}