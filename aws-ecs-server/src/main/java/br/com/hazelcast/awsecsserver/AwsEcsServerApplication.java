package br.com.hazelcast.awsecsserver;

import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.io.FileNotFoundException;

@SpringBootApplication
public class AwsEcsServerApplication {

    private static final String CONF_FILE_PATH = "hazelcast.config";

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(AwsEcsServerApplication.class, args);
    }

    @Bean
    public Config getConfig() throws FileNotFoundException {
        String confPath = env.getProperty(CONF_FILE_PATH);
        if (confPath != null) {
            return new XmlConfigBuilder(confPath).build();
        }
        return new Config();
    }

    @Bean
    public HazelcastInstance hazelcastInstance(Config config) {
        return Hazelcast.newHazelcastInstance(config);
    }

}
