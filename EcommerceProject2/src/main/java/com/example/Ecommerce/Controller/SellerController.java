package com.example.Ecommerce.Controller;

import com.example.Ecommerce.Service.SellerService;
import com.example.Ecommerce.Dto.SellerRequestDTO;
import com.example.Ecommerce.Dto.SellerResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController
{

    @Autowired
    private SellerService sellerService;


    @PostMapping("/save")
    public SellerResponseDTO saveSeller(@Valid @RequestBody SellerRequestDTO sellerRequestDTO) {

        return sellerService.saveSeller(sellerRequestDTO);
    }


    @GetMapping("/getAll")
    public List<SellerResponseDTO> getAllSellers() {

        return sellerService.getAllSellers();
    }


    @GetMapping("/get/{id}")
    public SellerResponseDTO getSellerById(@PathVariable Long id) {

        return sellerService.getSellerById(id);
    }


    @PutMapping("/update/{id}")
    public SellerResponseDTO updateSeller(@PathVariable Long id,
                                          @Valid @RequestBody SellerRequestDTO sellerRequestDTO) {

        return sellerService.updateSeller(id, sellerRequestDTO);
    }


    @DeleteMapping("/delete/{id}")
    public String deleteSeller(@PathVariable Long id) {

        return sellerService.deleteSeller(id);
    }
}