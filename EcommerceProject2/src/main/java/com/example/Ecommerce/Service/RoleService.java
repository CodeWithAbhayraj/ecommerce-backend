package com.example.Ecommerce.Service;

import com.example.Ecommerce.Dto.RoleRequestDto;
import com.example.Ecommerce.Dto.RoleResponseDto;

import java.util.List;

public interface RoleService {

    RoleResponseDto createRole(RoleRequestDto dto);

    RoleResponseDto getRole(Long id);

    List<RoleResponseDto> getAllRoles();

    RoleResponseDto updateRole(Long id, RoleRequestDto dto);

    void deleteRole(Long id);
}