package com.example.Ecommerce.Service;

import com.example.Ecommerce.Dto.SellerRequestDTO;
import com.example.Ecommerce.Dto.SellerResponseDTO;

import java.util.List;

public interface SellerService {

    SellerResponseDTO saveSeller(SellerRequestDTO sellerRequestDTO);

    List<SellerResponseDTO> getAllSellers();

    SellerResponseDTO getSellerById(Long id);

    SellerResponseDTO updateSeller(Long id, SellerRequestDTO sellerRequestDTO);

    String deleteSeller(Long id);
}