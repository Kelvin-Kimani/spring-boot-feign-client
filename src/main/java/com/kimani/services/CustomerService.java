package com.kimani.services;

import com.kimani.clients.CustomerClientService;
import com.kimani.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Since 18/12/2022
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */


@Slf4j
@Service
public record CustomerService(CustomerClientService clientService) {

    public void createCustomer(Customer customer) {
        clientService.createCustomer(customer);
    }

    public List<Customer> getAllCustomers() {
        return clientService.getAllCustomers();
    }

    public Customer getCustomerById(String id) {
        return clientService.getCustomerById(id);
    }
}
