package com.example.Ecommerce.Service;

import com.example.Ecommerce.Dto.CustomerRequestDTO;
import com.example.Ecommerce.Dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {

    CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO);

    List<CustomerResponseDTO> getAllCustomers();

    CustomerResponseDTO getCustomerById(Long id);

    CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO customerRequestDTO);

    String deleteCustomer(Long id);
}