package com.kimani.clients;

import com.kimani.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Since 18/12/2022
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */


@FeignClient(name = "customerClient", url = "http://localhost:8080")
public interface CustomerClientService {

    @PostMapping("/api/v1/customers")
    void createCustomer(@RequestBody Customer customer);

    @GetMapping("/api/v1/customers")
    List<Customer> getAllCustomers();

    @GetMapping("/api/v1/customers/{id}")
    Customer getCustomerById(@PathVariable String id);

}
