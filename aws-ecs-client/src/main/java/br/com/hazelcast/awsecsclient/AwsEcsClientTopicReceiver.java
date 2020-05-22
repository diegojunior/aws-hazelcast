package br.com.hazelcast.awsecsclient;

import br.com.hazelcast.awsecsbean.Mensagem;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;
import com.hazelcast.topic.Message;
import com.hazelcast.topic.ReliableMessageListener;

import java.util.HashSet;
import java.util.Set;

public class AwsEcsClientTopicReceiver {

    private HazelcastInstance hazelcastInstance;

    private Set<Mensagem> mensagens;

    public AwsEcsClientTopicReceiver(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
        this.mensagens = new HashSet<>();
        ITopic<Mensagem> topic = hazelcastInstance.getReliableTopic("msg");
        topic.addMessageListener(new AwsEcsClientTopicReceiver.MsgListener());
    }

    public Mensagem read(String key) {
        return mensagens.stream().filter((item) -> key.equalsIgnoreCase(item.getKey())).findFirst().orElse(null);
    }

    class MsgListener implements ReliableMessageListener<Mensagem> {

        @Override
        public long retrieveInitialSequence() {
            return 0;
        }

        @Override
        public void storeSequence(long l) {

        }

        @Override
        public boolean isLossTolerant() {
            return false;
        }

        @Override
        public boolean isTerminal(Throwable throwable) {
            return false;
        }

        @Override
        public void onMessage(Message<Mensagem> message) {
            Mensagem msg = message.getMessageObject();
            System.out.println("Recebendo a mensagem: #### " + msg + " #####");
            mensagens.add(msg);
        }
    }


}
