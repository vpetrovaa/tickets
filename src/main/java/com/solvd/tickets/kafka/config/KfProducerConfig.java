package com.solvd.tickets.kafka.config;

import com.solvd.tickets.kafka.property.KfProperty;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KfProducerConfig {

    private final KfProperty kfProperty;

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(kfProperty.getTopic())
                .partitions(kfProperty.getPartitions())
                .replicas(kfProperty.getReplicas())
                .build();
    }

    @Bean
    public SenderOptions<String, String> sendOptions() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kfProperty.getPort());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return SenderOptions.create(config);
    }

    @Bean
    public KafkaSender<String, String> kafkaSender() {
        return KafkaSender.create(sendOptions());
    }

}
