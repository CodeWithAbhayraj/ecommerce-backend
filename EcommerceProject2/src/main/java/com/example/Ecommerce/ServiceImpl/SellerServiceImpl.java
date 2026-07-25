package com.example.Ecommerce.ServiceImpl;

import com.example.Ecommerce.Model.Role;
import com.example.Ecommerce.Exception.DuplicateEmailException;
import com.example.Ecommerce.Exception.ResourceNotFoundException;
import com.example.Ecommerce.Model.Seller;
import com.example.Ecommerce.Repository.RoleRepository;
import com.example.Ecommerce.Repository.SellerRepository;
import com.example.Ecommerce.Service.SellerService;
import com.example.Ecommerce.Dto.SellerRequestDTO;
import com.example.Ecommerce.Dto.SellerResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;




    // RequestDTO -> Entity
    private Seller mapToEntity(SellerRequestDTO dto) {

        Seller seller = modelMapper.map(dto, Seller.class);

        seller.setPassword(passwordEncoder.encode(dto.getPassword()));
        Role role = roleRepository.findByRoleName("SELLER")
                .orElseThrow(() -> new RuntimeException("SELLER role not found"));

        seller.setRole(role);

        return seller;
    }




    // Entity -> ResponseDTO
    private SellerResponseDTO mapToResponse(Seller seller) {

        SellerResponseDTO dto = new SellerResponseDTO();

        dto.setId(seller.getId());
        dto.setName(seller.getName());
        dto.setEmail(seller.getEmail());
        dto.setShopName(seller.getShopName());
        dto.setShopAddress(seller.getShopAddress());

        dto.setRoleName(seller.getRole().getRoleName());

        return dto;
    }




    @Override
    public SellerResponseDTO saveSeller(SellerRequestDTO sellerRequestDTO) {

        if(sellerRepository.existsByEmail(sellerRequestDTO.getEmail())){
            throw new DuplicateEmailException("Email already exists.");
        }

        Seller seller = modelMapper.map(sellerRequestDTO, Seller.class);

        seller.setPassword(passwordEncoder.encode(sellerRequestDTO.getPassword()));

        Role role = roleRepository.findByRoleName("SELLER")
                .orElseThrow(() -> new RuntimeException("Role Not Found"));

        seller.setRole(role);
//        seller.setRoleName(role.getRoleName());

        Seller savedSeller = sellerRepository.save(seller);

        return modelMapper.map(savedSeller, SellerResponseDTO.class);
    }





    @Override
    public List<SellerResponseDTO> getAllSellers() {

        return sellerRepository.findAll()
                .stream()
                .map(seller -> modelMapper.map(seller, SellerResponseDTO.class))
                .toList();
    }





    @Override
    public SellerResponseDTO getSellerById(Long id) {

        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Seller not found with id : " + id));

        return modelMapper.map(seller, SellerResponseDTO.class);
    }





    @Override
    public SellerResponseDTO updateSeller(Long id, SellerRequestDTO dto) {

        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Seller not found with id : " + id));

        seller.setName(dto.getName());
        seller.setEmail(dto.getEmail());
        seller.setPassword(passwordEncoder.encode(dto.getPassword()));
        seller.setShopName(dto.getShopName());
        seller.setShopAddress(dto.getShopAddress());

        Seller updatedSeller = sellerRepository.save(seller);

        return modelMapper.map(updatedSeller, SellerResponseDTO.class);
    }




    @Override
    public String deleteSeller(Long id) {

        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Seller not found with id : " + id));

        sellerRepository.delete(seller);

        return "Seller Deleted Successfully";
    }
    }