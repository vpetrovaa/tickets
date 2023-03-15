package com.solvd.tickets.kafka;

import com.solvd.tickets.kafka.property.KfProperty;
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

    private final KfProperty kfProperty;
    private final KafkaSender<String, String> kafkaSender;

    @Override
    public void send(String message) {
        this.kafkaSender.send(Mono.just(SenderRecord.create(
                        kfProperty.getTopic(),
                        2,
                        System.currentTimeMillis(),
                        kfProperty.getKey(),
                        message,
                        null)))
                .subscribe();
    }

}
