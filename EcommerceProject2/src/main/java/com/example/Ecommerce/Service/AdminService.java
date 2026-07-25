package com.example.Ecommerce.Service;

import com.example.Ecommerce.Dto.AdminRequestDTO;
import com.example.Ecommerce.Dto.AdminResponseDTO;

import java.util.List;


public interface AdminService {

    AdminResponseDTO saveAdmin(AdminRequestDTO adminRequestDTO);

    List<AdminResponseDTO> getAllAdmins();

    AdminResponseDTO getAdminById(Long id);

    AdminResponseDTO updateAdmin(Long id, AdminRequestDTO adminRequestDTO);

    String deleteAdmin(Long id);
}


