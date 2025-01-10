package org.example.tutorial;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/add")
    public String addCustomer(@RequestParam String first, @RequestParam String last) {
        Customer customer = new Customer();
        customer.setFirstName(first);
        customer.setLastName(last);
        customerRepository.save(customer);
        return "Added new customer to repo!";
    }

    @GetMapping("/list")
    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public Customer findCustomerById(@PathVariable Integer id) {
        return customerRepository.findCustomerById(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        customerRepository.deleteById(id);
    }

    @PutMapping ("/update")
    public String updateCustomer(@RequestParam String first, @RequestParam String last, @RequestParam Integer id) {
        Customer customer = customerRepository.findCustomerById(id);

        if (customer != null) {
            customer.setFirstName(first);
            customer.setLastName(last);
            customerRepository.save(customer);
        }

        return "Customer updated!" + customer.getFirstName() + " " + customer.getLastName() + " " + customer.getId();
    }
}