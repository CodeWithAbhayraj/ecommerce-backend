package com.example.Ecommerce.ServiceImpl;

import com.example.Ecommerce.Model.Role;
import com.example.Ecommerce.Exception.DuplicateEmailException;
import com.example.Ecommerce.Exception.ResourceNotFoundException;
import com.example.Ecommerce.Model.Admin;
import com.example.Ecommerce.Repository.AdminRepository;
import com.example.Ecommerce.Repository.RoleRepository;
import com.example.Ecommerce.Service.AdminService;
import com.example.Ecommerce.Dto.AdminRequestDTO;
import com.example.Ecommerce.Dto.AdminResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private RoleRepository roleRepository;


    // RequestDTO -> Entity
    private Admin mapToEntity(AdminRequestDTO dto) {

        Admin admin = modelMapper.map(dto, Admin.class);

        admin.setPassword(passwordEncoder.encode(dto.getPassword()));
        Role role = roleRepository.findByRoleName("ADMIN")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        admin.setRole(role);

        return admin;
    }


    // Entity -> ResponseDTO
    private AdminResponseDTO mapToResponse(Admin admin) {

        AdminResponseDTO dto = new AdminResponseDTO();

        dto.setId(admin.getId());
        dto.setName(admin.getName());
        dto.setEmail(admin.getEmail());
        dto.setAdminCode(admin.getAdminCode());

        dto.setRoleName(admin.getRole().getRoleName());

        return dto;
    }


    @Override
    public AdminResponseDTO saveAdmin(AdminRequestDTO adminRequestDTO) {

        // Check if email already exists
        if (adminRepository.existsByEmail(adminRequestDTO.getEmail())) {
            throw new DuplicateEmailException("Email already exists: " + adminRequestDTO.getEmail());
        }

        // DTO -> Entity
        Admin admin = modelMapper.map(adminRequestDTO, Admin.class);

        // Encrypt Password
        admin.setPassword(passwordEncoder.encode(adminRequestDTO.getPassword()));

        // Set Role
        Role role = roleRepository.findByRoleName("ADMIN")
                .orElseThrow(() -> new RuntimeException("Role Not Found"));

        admin.setRole(role);
//        admin.setRoleName(role.getRoleName());

        // Save
        Admin savedAdmin = adminRepository.save(admin);

        // Entity -> ResponseDTO
        return modelMapper.map(savedAdmin, AdminResponseDTO.class);
    }


    @Override
    public List<AdminResponseDTO> getAllAdmins() {

        List<Admin> admins = adminRepository.findAll();

        return admins.stream()
                .map(admin -> modelMapper.map(admin, AdminResponseDTO.class))
                .toList();
    }


    @Override
    public AdminResponseDTO getAdminById(Long id) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Admin not found with id : " + id));

        return mapToResponse(admin);
    }


    @Override
    public AdminResponseDTO updateAdmin(Long id, AdminRequestDTO dto) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Admin not found with id : " + id));

        admin.setName(dto.getName());
        admin.setEmail(dto.getEmail());
        admin.setPassword(passwordEncoder.encode(dto.getPassword()));
        admin.setAdminCode(dto.getAdminCode());

        Admin updatedAdmin = adminRepository.save(admin);

        return mapToResponse(updatedAdmin);
    }


    @Override
    public String deleteAdmin(Long id) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Admin not found with id : " + id));

        adminRepository.delete(admin);

        return "Admin Deleted Successfully";
    }
}