package com.himorithm.ignitejdbc.client.run;

import com.himorithm.ignitejdbc.client.configuration.ConnectionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientRunner implements ApplicationRunner {

    private final ConnectionManager configuration;

    @Autowired
    public ClientRunner(ConnectionManager configuration) {
        this.configuration = configuration;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Started APP");
        configuration.connection();
    }
}
