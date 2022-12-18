package com.kimani.controllers;

import com.kimani.dto.Customer;
import com.kimani.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Since 18/12/2022
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */


@RestController
@RequestMapping("/api/feign/v1/customers")
public record CustomerController(CustomerService customerService) {


    @PostMapping
    void createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
    }

    @GetMapping
    List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("{id}")
    Customer getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

}
