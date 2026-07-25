package com.example.Ecommerce.Controller;

import com.example.Ecommerce.Dto.ProductDto.ProductRequestDto;
import com.example.Ecommerce.Dto.ProductDto.ProductResponseDto;
import com.example.Ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ProductResponseDto save(@RequestBody ProductRequestDto dto){
        return productService.save(dto);
    }

    @GetMapping("/get/{id}")
    public ProductResponseDto getById(@PathVariable Long id){
        return productService.getById(id);
    }

    @GetMapping("/getAll")
    public List<ProductResponseDto> getAll(){
        return productService.getAll();
    }

    @PutMapping("/update/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto dto){
        return productService.update(id,dto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        productService.delete(id);

        return "Product Deleted Successfully";
    }

}