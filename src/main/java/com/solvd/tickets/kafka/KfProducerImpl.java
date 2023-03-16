package com.solvd.tickets.kafka;

import com.solvd.tickets.kafka.property.KfProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Slf4j
@Component
@RequiredArgsConstructor
public class KfProducerImpl implements KfProducer{

    private final KfProperties kfProperties;
    private final KafkaSender<String, String> kafkaSender;

    @Override
    public void send(String message) {
        this.kafkaSender.send(Mono.just(SenderRecord.create(
                        kfProperties.getTopic(),
                        2,
                        System.currentTimeMillis(),
                        kfProperties.getKey(),
                        message,
                        null)))
                .subscribe();
    }

}
