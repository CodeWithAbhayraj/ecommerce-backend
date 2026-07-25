package com.example.Ecommerce.Controller;

import com.example.Ecommerce.Service.AdminService;
import com.example.Ecommerce.Dto.AdminRequestDTO;
import com.example.Ecommerce.Dto.AdminResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/save")
    public AdminResponseDTO saveAdmin(@Valid  @RequestBody AdminRequestDTO adminRequestDTO) {

        return adminService.saveAdmin(adminRequestDTO);
    }

    @GetMapping("/getAll")
    public List<AdminResponseDTO> getAllAdmins() {

        return adminService.getAllAdmins();
    }

    @GetMapping("/get/{id}")
    public AdminResponseDTO getAdminById(@PathVariable Long id) {

        return adminService.getAdminById(id);
    }

    @PutMapping("/update/{id}")
    public AdminResponseDTO updateAdmin(@PathVariable Long id,  @Valid
                                        @RequestBody AdminRequestDTO adminRequestDTO) {

        return adminService.updateAdmin(id, adminRequestDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable Long id) {

        return adminService.deleteAdmin(id);
    }
}