package com.example.Ecommerce.Controller;

import com.example.Ecommerce.Service.CustomerService;
import com.example.Ecommerce.Dto.CustomerRequestDTO;
import com.example.Ecommerce.Dto.CustomerResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public CustomerResponseDTO saveCustomer(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {

        return customerService.saveCustomer(customerRequestDTO);
    }

    @GetMapping("/getAll")
    public List<CustomerResponseDTO> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    @GetMapping("/get/{id}")
    public CustomerResponseDTO getCustomerById(@PathVariable Long id) {

        return customerService.getCustomerById(id);
    }

    @PutMapping("/update/{id}")
    public CustomerResponseDTO updateCustomer(@PathVariable Long id,
                                              @Valid @RequestBody CustomerRequestDTO customerRequestDTO) {

        return customerService.updateCustomer(id, customerRequestDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {

        return customerService.deleteCustomer(id);
    }
}