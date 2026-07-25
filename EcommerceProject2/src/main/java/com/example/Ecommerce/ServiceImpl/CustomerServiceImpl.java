package com.example.Ecommerce.ServiceImpl;

import com.example.Ecommerce.Model.Role;
import com.example.Ecommerce.Exception.DuplicateEmailException;
import com.example.Ecommerce.Exception.ResourceNotFoundException;
import com.example.Ecommerce.Model.Customer;
import com.example.Ecommerce.Repository.CustomerRepository;
import com.example.Ecommerce.Repository.RoleRepository;
import com.example.Ecommerce.Service.CustomerService;
import com.example.Ecommerce.Dto.CustomerRequestDTO;
import com.example.Ecommerce.Dto.CustomerResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private RoleRepository roleRepository;


    // RequestDTO -> Entity
    private Customer mapToEntity(CustomerRequestDTO dto) {

        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateEmailException("Email already exists.");
        }

        Customer customer = modelMapper.map(dto, Customer.class);

        customer.setPassword(passwordEncoder.encode(dto.getPassword()));
        Role role = roleRepository.findByRoleName("CUSTOMER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        customer.setRole(role);

        return customer;
    }


    // Entity -> ResponseDTO
    private CustomerResponseDTO mapToResponse(Customer customer) {

        CustomerResponseDTO dto = new CustomerResponseDTO();

        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setAddress(customer.getAddress());
        dto.setMobile(customer.getMobile());

        dto.setRoleName(customer.getRole().getRoleName());

        return dto;
    }


    @Override
    public CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO) {

        if (customerRepository.existsByEmail(customerRequestDTO.getEmail())) {
            throw new DuplicateEmailException("Email already exists: " + customerRequestDTO.getEmail());
        }

        Customer customer = modelMapper.map(customerRequestDTO, Customer.class);

        customer.setPassword(passwordEncoder.encode(customerRequestDTO.getPassword()));

        Role role = roleRepository.findByRoleName("CUSTOMER")
                .orElseThrow(() -> new RuntimeException("Role Not Found"));

        customer.setRole(role);
//        customer.setRoleName(role.getRoleName());

        Customer savedCustomer = customerRepository.save(customer);

        return modelMapper.map(savedCustomer, CustomerResponseDTO.class);
    }


    @Override
    public List<CustomerResponseDTO> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerResponseDTO.class))
                .toList();
    }


    @Override
    public CustomerResponseDTO getCustomerById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found with id : " + id));

        return modelMapper.map(customer, CustomerResponseDTO.class);
    }


    @Override
    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO dto) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found with id : " + id));

        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPassword(passwordEncoder.encode(dto.getPassword()));
        customer.setAddress(dto.getAddress());
        customer.setMobile(dto.getMobile());

        Customer updatedCustomer = customerRepository.save(customer);

        return modelMapper.map(updatedCustomer, CustomerResponseDTO.class);
    }


    @Override
    public String deleteCustomer(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found with id : " + id));

        customerRepository.delete(customer);

        return "Customer Deleted Successfully";
    }
}