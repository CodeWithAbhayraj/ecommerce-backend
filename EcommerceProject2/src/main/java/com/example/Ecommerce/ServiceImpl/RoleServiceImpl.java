package com.example.Ecommerce.ServiceImpl;

import com.example.Ecommerce.Dto.RoleRequestDto;
import com.example.Ecommerce.Dto.RoleResponseDto;
import com.example.Ecommerce.Model.Role;
import com.example.Ecommerce.Repository.RoleRepository;
import com.example.Ecommerce.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleResponseDto createRole(RoleRequestDto dto) {

        Role role = new Role();
        role.setRoleName(dto.getRoleName());

        Role savedRole = roleRepository.save(role);

        return mapToDto(savedRole);
    }

    @Override
    public RoleResponseDto getRole(Long id) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id : " + id));

        return mapToDto(role);
    }

    @Override
    public List<RoleResponseDto> getAllRoles() {

        return roleRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponseDto updateRole(Long id, RoleRequestDto dto) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id : " + id));

        role.setRoleName(dto.getRoleName());

        Role updatedRole = roleRepository.save(role);

        return mapToDto(updatedRole);
    }

    @Override
    public void deleteRole(Long id) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id : " + id));

        roleRepository.delete(role);
    }

    private RoleResponseDto mapToDto(Role role) {

        RoleResponseDto dto = new RoleResponseDto();

        dto.setId(role.getId());
        dto.setRoleName(role.getRoleName());

        return dto;
    }
}