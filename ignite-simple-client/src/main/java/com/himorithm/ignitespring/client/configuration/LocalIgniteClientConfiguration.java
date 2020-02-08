package com.himorithm.ignitespring.client.configuration;

import com.himorithm.ignitespring.client.model.Customer;
import com.himorithm.ignitespring.client.model.LocalCache;
import com.himorithm.ignitespring.client.model.Seller;
import lombok.Getter;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class LocalIgniteClientConfiguration {
    private final String clusterName;

    public LocalIgniteClientConfiguration(
            @Value("${local.cluster.name}") String clusterName) {
        this.clusterName = clusterName;
    }

    @Bean
    public Ignite igniteInstance() {
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setIgniteInstanceName(clusterName);
        cfg.setPeerClassLoadingEnabled(true);
        cfg.setClientMode(true);
        cfg.setLifecycleBeans(new ClientLifeCycleBean());
        return Ignition.start(cfg);
    }

    public CacheConfiguration<Long, Customer> getCustomerCacheConfiguration() {
        CacheConfiguration<Long, Customer> cfg = new CacheConfiguration<>(LocalCache.CUSTOMER);
        cfg.setIndexedTypes(Long.class, Customer.class);
        return cfg;
    }

    public CacheConfiguration<Integer, Seller> getSellerCacheConfiguration() {
        CacheConfiguration<Integer, Seller> cfg = new CacheConfiguration<>(LocalCache.SELLER);
        cfg.setIndexedTypes(Integer.class, Customer.class);
        return cfg;
    }
}
