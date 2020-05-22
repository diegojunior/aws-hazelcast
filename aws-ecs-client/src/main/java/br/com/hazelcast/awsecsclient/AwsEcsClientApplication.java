package br.com.hazelcast.awsecsclient;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientConfigXmlGenerator;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.io.IOException;

@SpringBootApplication
public class AwsEcsClientApplication {

    private static final String CONF_FILE_PATH = "hazelcast.client.config";

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(AwsEcsClientApplication.class, args);
    }

    @Bean
    public ClientConfig clientConfig() throws IOException {
        String confPath = env.getProperty(CONF_FILE_PATH);
        if (confPath != null) {
            return new XmlClientConfigBuilder(confPath).build();
        }
        return new ClientConfig();
    }

    @Bean
    public HazelcastInstance hazelcastClientInstance(ClientConfig clientConfig) {
        return HazelcastClient.newHazelcastClient(clientConfig);
    }

    @Bean
    public AwsEcsClientTopicReceiver awsEcsClientTopicReceiver(HazelcastInstance hazelcastInstance) {
        return new AwsEcsClientTopicReceiver(hazelcastInstance);
    }

}
