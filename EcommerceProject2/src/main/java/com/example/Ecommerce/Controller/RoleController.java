package com.example.Ecommerce.Controller;

import com.example.Ecommerce.Dto.RoleRequestDto;
import com.example.Ecommerce.Dto.RoleResponseDto;
import com.example.Ecommerce.Service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // Create Role
    @PostMapping
    public ResponseEntity<RoleResponseDto> createRole(
            @Valid @RequestBody RoleRequestDto dto) {

        RoleResponseDto response = roleService.createRole(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get Role By Id
    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDto> getRoleById(@PathVariable Long id) {

        RoleResponseDto response = roleService.getRole(id);
        return ResponseEntity.ok(response);
    }

    // Get All Roles
    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> getAllRoles() {

        List<RoleResponseDto> response = roleService.getAllRoles();
        return ResponseEntity.ok(response);
    }

    // Update Role
    @PutMapping("/{id}")
    public ResponseEntity<RoleResponseDto> updateRole(
            @PathVariable Long id,
            @Valid @RequestBody RoleRequestDto dto) {

        RoleResponseDto response = roleService.updateRole(id, dto);
        return ResponseEntity.ok(response);
    }

    // Delete Role
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {

        roleService.deleteRole(id);
        return ResponseEntity.ok("Role deleted successfully");
    }
}