package com.colak.springtutorial.config;

import com.hazelcast.config.Config;
import com.hazelcast.jet.config.JetConfig;
import com.hazelcast.spi.properties.ClusterProperty;
import com.hazelcast.spring.context.SpringManagedContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    public Config config(SpringManagedContext springManagedContext) {
        Config config = new Config();
        config.setProperty(ClusterProperty.PHONE_HOME_ENABLED.getName(), "false");

        // Add JetConfig
        JetConfig jetConfig = config.getJetConfig();
        jetConfig.setEnabled(true);
        jetConfig.setResourceUploadEnabled(true);

        // Set the SpringManagedContext
        config.setManagedContext(springManagedContext);

        // You can configure other settings here
        config.setClusterName("my-cluster");

        return config;
    }

    @Bean
    public SpringManagedContext springManagedContext() {
        return new SpringManagedContext();
    }
}
