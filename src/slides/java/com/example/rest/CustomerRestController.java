// Folie 262 – RestController - GET
// Folie 264 – RestController - POST
package com.example.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pizza.customer.Customer;
import pizza.customer.CustomerService;

@RestController
public class CustomerRestController {

    private static final String ROOT = "/customers";
    public static final String GET_ALL_ENDPOINT = ROOT;
    public static final String CREATE_ENDPOINT = ROOT;

    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // --- Folie 262 ---

    @GetMapping(GET_ALL_ENDPOINT)
    public Iterable<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // --- Folie 264 ---
    // Achtung: Customer hat zwei Konstruktoren und keinen Default-Konstruktor, Jackson kann es so nicht
    // deserialisieren. Der Hauptcode (pizza.customer.CustomerRestController) nimmt daher einen
    // CreateCustomerRequest-Record entgegen.

    @PostMapping(CREATE_ENDPOINT)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer c) {
        return customerService.createCustomer(c);
    }
}
