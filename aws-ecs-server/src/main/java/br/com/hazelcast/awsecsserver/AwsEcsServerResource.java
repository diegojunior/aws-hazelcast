package br.com.hazelcast.awsecsserver;

import br.com.hazelcast.awsecsbean.Mensagem;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.topic.ITopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AwsEcsServerResource {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @RequestMapping("/put")
    public void put(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value) {
        ITopic<Mensagem> topic = hazelcastInstance.getReliableTopic("msg");
        Mensagem mensagem = new Mensagem(key, value);
        System.out.println("Publicando msg no server :" + mensagem);
        topic.publish(mensagem);

        IMap<String, Mensagem> map = hazelcastInstance.getMap("map");
        map.put(key, mensagem);
    }

    @RequestMapping("/get")
    public Mensagem get(@RequestParam(value = "key") String key) {
        IMap<String, Mensagem> map = hazelcastInstance.getMap("map");
        return map.get(key);
    }

    @RequestMapping("/")
    public String get() {
        System.out.println("HealthCheck OK");
        return "ok";
    }
}
