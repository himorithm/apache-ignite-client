package com.himorithm.ignitespring.client.run;

import com.himorithm.ignitespring.client.configuration.LocalIgniteClientConfiguration;
import com.himorithm.ignitespring.client.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.TextQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.cache.Cache;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ClientRunner implements ApplicationRunner {

    private final LocalIgniteClientConfiguration configuration;
    private final Ignite igniteInstance;

    @Autowired
    public ClientRunner(LocalIgniteClientConfiguration configuration, Ignite igniteInstance) {
        this.configuration = configuration;
        this.igniteInstance = igniteInstance;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        IgniteCache<Long, Customer> customerCache = igniteInstance.getOrCreateCache(configuration.getCustomerCacheConfiguration());
        customerCache.put(1L, new Customer(1L, "Himanshu S", "Ahire"));
        customerCache.put(2L, new Customer(2L, "Anti D", "Himanshu V"));

        TextQuery<Long, Customer> qury = new TextQuery<>(Customer.class, "Himanshu");
        QueryCursor<Cache.Entry<Long, Customer>> queryCursor = customerCache.query(qury);
        List<Cache.Entry<Long, Customer>> all = queryCursor.getAll();
        List<Customer> customerList = all.stream().map(Cache.Entry::getValue).collect(Collectors.toList());
        log.info("All Customers for Submitted Text Query: {}", customerList);
        Customer customer = customerCache.get(2L);
        log.info("Customer Found :{}", customer);

    }
}
