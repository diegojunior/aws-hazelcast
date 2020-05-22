package br.com.hazelcast.awsecsclient;

import br.com.hazelcast.awsecsbean.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AwsEcsClientResource {


    @Autowired
    private AwsEcsClientTopicReceiver awsEcsClientTopicReceiver;


    @RequestMapping("/get")
    public Mensagem get(@RequestParam(value = "key") String key) {
        System.out.println("Lendo a configuração: " + key);
        return awsEcsClientTopicReceiver.read(key);
    }

}
