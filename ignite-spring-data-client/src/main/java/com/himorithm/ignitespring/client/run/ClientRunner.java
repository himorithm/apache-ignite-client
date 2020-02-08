package com.himorithm.ignitespring.client.run;

import com.himorithm.ignitespring.client.model.Customer;
import com.himorithm.ignitespring.client.repo.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.Ignite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class ClientRunner implements ApplicationRunner {

    private final CustomerRepository customerRepository;
    private final Ignite igniteInstance;

    @Autowired
    public ClientRunner(CustomerRepository customerRepository, Ignite igniteInstance) {
        this.customerRepository = customerRepository;
        this.igniteInstance = igniteInstance;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        customerRepository.save(3L, new Customer(3L, "Himanshu V", " Ahire"));
        customerRepository.save(4L, new Customer(4L, "Himani S", " Eriha"));
        Optional<Customer> customer = customerRepository.findById(3L);
        if (customer.isPresent()) {
            Customer customer1 = customer.get();
            customer.ifPresent(c -> log.info("Found Customer:{}", customer1));
        }

    }
}
