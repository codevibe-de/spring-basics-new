// Folie 146 – Warum überhaupt konfigurieren? (Negativbeispiel: hart kodierte URL)
package com.example.properties;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private String ldapUrl = "https://www.example.com/ldap";
    // ...
}
