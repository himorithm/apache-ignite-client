package com.himorithm.ignitespring.client.repo;

import com.himorithm.ignitespring.client.model.Customer;
import org.apache.ignite.springdata20.repository.IgniteRepository;
import org.apache.ignite.springdata20.repository.config.RepositoryConfig;

@RepositoryConfig(cacheName = "CustomerCache")
public interface CustomerRepository extends IgniteRepository<Customer, Long> {

}
